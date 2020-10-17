package com.example.travada.features.topup

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.NumberFormat
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.transfer.transferbank.TransferInvoiceActivity
import com.example.travada.features.transfer.va.VAInvoiceActivity
import com.example.travada.mainpage.MainPageActivity
import com.example.travada.util.util
import id.zelory.cekrek.Cekrek
import id.zelory.cekrek.config.CanvasSize
import kotlinx.android.synthetic.main.activity_top_up_invoice.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class TopUpInvoiceActivity : AppCompatActivity(), TopUpInvoicePresenter.Listener {
    private lateinit var presenter: TopUpInvoicePresenter
    private lateinit var bundle: Bundle
    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_invoice)
        presenter = TopUpInvoicePresenter(this)
        intent?.extras?.let { bundle = it }
        notificationManager = NotificationManagerCompat.from(this)

        Glide
            .with(this)
            .load(R.drawable.image_login)
            .into(iv_image)

        btn_back.setOnClickListener {
            goToMainPage()
        }

        setInformation(bundle)

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
            SimpleDateFormat(TransferInvoiceActivity.FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())
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

        val picture: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.image_notif2)

        val builder = androidx.core.app.NotificationCompat.Builder(this, util.NOTIF_CHANNEL1)
            .setSmallIcon(R.drawable.ic_download_blue)
            .setContentTitle("Bukti topup berhasil didownload")
            .setContentText(uriForNotif.toString())
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setLargeIcon(picture)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle())
            .setAutoCancel(true)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)

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
        requestPermissions(
            TransferInvoiceActivity.arrayListPermission,
            TransferInvoiceActivity.REQUEST_CODE
        )
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
            TransferInvoiceActivity.REQUEST_CODE -> {
                //Loop semua permission apakah diizinkan atau tidak.
                for (i in permissions.indices) {
                    if ((permissions[i] == TransferInvoiceActivity.arrayListPermission[i]) && (grantResults[i] == PackageManager.PERMISSION_GRANTED)) {
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

    override fun goToMainPage() {
        val intent = Intent(this, MainPageActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    override fun setInformation(bundle: Bundle) {
        val createdAt = bundle.getString("createdAt").toString()
        val nominal = bundle.getString("nominal")?.toLong()
        val id = bundle.getString("id").toString()
        val namaAsal = bundle.getString("namaAsal").toString()
        val namaTujuan = bundle.getString("namaTujuan").toString()
        val bankAsal = bundle.getString("bankAsal").toString()
        val rekeningAsal = bundle.getString("rekeningAsal").toString()
        val bankTujuan = bundle.getString("bankTujuan").toString()
        val rekeningTujuan = bundle.getString("rekeningTujuan").toString()

        var tanggal = createdAt.subSequence(8,10).toString()
        var bulan = createdAt.subSequence(5,7).toString()
        var tahun = createdAt.subSequence(0,4).toString()
        var time = createdAt.substring(11,16).toString()

        var namaBulan = ""
        when(bulan){
            "01" -> namaBulan = "Januari"
            "02" -> namaBulan = "Februari"
            "03" -> namaBulan = "Maret"
            "04" -> namaBulan = "April"
            "05" -> namaBulan = "Mei"
            "06" -> namaBulan = "Juni"
            "07" -> namaBulan = "Juli"
            "08" -> namaBulan = "Agustus"
            "09" -> namaBulan = "September"
            "10" -> namaBulan = "Oktober"
            "11" -> namaBulan = "November"
            "12" -> namaBulan = "Desember"
        }

        var timestamp = "$tanggal $namaBulan $tahun - $time"

        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)


        tv_timestamp.text = timestamp
        tv_fromName.text = namaAsal
        tv_fromBank.text = bankAsal
        tv_fromNumb.text = StringBuilder(rekeningAsal).insert(4, ' ').insert(9, ' ').insert(14, ' ').toString()
        tv_toName.text = namaTujuan
        tv_amount.text = numberFormat.format(nominal)
        tv_detail_id.text = id

    }
}