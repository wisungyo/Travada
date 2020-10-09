package com.example.travada.features.transfer.transferbank

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.CATEGORY_MESSAGE
import androidx.core.app.NotificationCompat.PRIORITY_HIGH
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.transfer.TransferMenuActivity
import com.example.travada.features.transfer.va.VAInvoiceActivity
import com.example.travada.util.util
import id.zelory.cekrek.Cekrek
import id.zelory.cekrek.config.CanvasSize
import kotlinx.android.synthetic.main.activity_transfer_invoice.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class TransferInvoiceActivity : AppCompatActivity(), TransferInvoicePresenter.Listener {
    private lateinit var presenter: TransferInvoicePresenter
    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_invoice)
        presenter = TransferInvoicePresenter(this)
        notificationManager = NotificationManagerCompat.from(this)

        Glide
            .with(this)
            .load(R.drawable.image_login)
            .into(iv_image)




        btn_share.setOnClickListener {
            val bitmap = Cekrek.toBitmap(layout_print) {
                canvasConfig.width =
                    CanvasSize.Specific(layout_print.width)// set canvas size to 1280 px
            }
            shareBitmap(bitmap)
        }

        btn_download.setOnClickListener {
            val bitmap = Cekrek.toBitmap(layout_print) {
                canvasConfig.width =
                    CanvasSize.Specific(layout_print.width)// set canvas size to 1280 px
            }

            if (checkPermissions()) {
                saveBitmap(bitmap)
            } else {
                requestRequiredPermissions()
            }
        }

        btn_back.setOnClickListener {
            goToTransferMenu()
        }

    }

    companion object {
        val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        const val REQUEST_CODE = 205
        val arrayListPermission = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    fun saveBitmap(bitmap: Bitmap) {
        val getTime =
            SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())
                .toString()
        val imageFileName = "TravadaDownload_${getTime}.jpg"

        val outputDirectory = getOutputDirectory()

        val image: File = File(
            outputDirectory, imageFileName
        )

        val bitmap1 = bitmap

        if (!image.exists()) {
            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(image)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        var uriFile = Uri.fromFile(image)

        val cachePath = File(externalCacheDir, "my_images/")
        cachePath.mkdirs()
        val imageToShow = File(cachePath, imageFileName)
        var fos1: FileOutputStream? = null
        try {
            fos1 = FileOutputStream(imageToShow)
            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, fos1)
            fos1.flush()
            fos1.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val uriProvider: Uri = FileProvider.getUriForFile(
            this,
            applicationContext.packageName + ".provider",
            imageToShow
        )

        showNotif(uriProvider, uriFile)
    }

    fun showNotif(uriToShow: Uri, uriForNotif: Uri) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uriToShow, "image/*")
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val picture:Bitmap = BitmapFactory.decodeResource(resources, R.drawable.image_notif2)

        val builder = androidx.core.app.NotificationCompat.Builder(this, util.NOTIF_CHANNEL1)
            .setSmallIcon(R.drawable.ic_download_blue)
            .setContentTitle("Bukti transaksi berhasil didownload")
            .setContentText(uriForNotif.toString())
            .setContentIntent(pendingIntent)
            .setPriority(PRIORITY_HIGH)
            .setLargeIcon(picture)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle())
            .setAutoCancel(true)
            .setCategory(CATEGORY_MESSAGE)

        val notification = builder.build()
        notificationManager.notify(1, notification)
    }

    fun shareBitmap(bitmap: Bitmap) {
        val getTime =
            SimpleDateFormat(VAInvoiceActivity.FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())
                .toString()
        val imageFileName = "TravadaDownload_${getTime}.jpg"
        val cachePath = File(externalCacheDir, "my_images/")
        cachePath.mkdirs()

        val file = File(cachePath, imageFileName)
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val myImageFileUri: Uri = FileProvider.getUriForFile(
            this,
            applicationContext.packageName + ".provider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.putExtra(Intent.EXTRA_STREAM, myImageFileUri)
        intent.type = "image/png"
        startActivity(Intent.createChooser(intent, "Share with"))
    }

    fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, "Download").apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    //Mengecek semua permission yang dibutuhkan. Akan false jika setidaknya ada 1 permission yang belum mendapatkan izin.
    fun checkPermissions(): Boolean {
        return (
                (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                        (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                )
    }

    //melakukan semua request permission yang dibutuhkan aplikasi
    fun requestRequiredPermissions() {
        requestPermissions(arrayListPermission, REQUEST_CODE)
    }

    //Callback / respon dari konfirmasi permission (pilihan allow / deny)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //Cek request codenya sama seperti request code ketika meminta permission.
        when (requestCode) {
            REQUEST_CODE -> {
                //Loop semua permission apakah diizinkan atau tidak.
                for (i in permissions.indices) {
                    if ((permissions[i] == arrayListPermission[i]) && (grantResults[i] == PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(
                            this,
                            "Permission ${permissions[i]} Diizinkan",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "Permission ${permissions[i]} Ditolak",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }



    override fun goToTransferMenu() {
        val goToNextActivity = Intent(this, TransferMenuActivity::class.java)
        startActivity(goToNextActivity)
        finishAffinity()
    }
}