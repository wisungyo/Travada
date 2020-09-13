package com.example.travada.features.tabungan.form_one

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.media.MediaScannerConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.travada.R
import com.example.travada.features.tabungan.DataPermissions.Companion.CAMERA_REQUEST
import com.example.travada.features.tabungan.DataPermissions.Companion.GALLERY_REQUEST
import com.example.travada.features.tabungan.DataPermissions.Companion.REQUEST_CODE
import com.example.travada.features.tabungan.DataPermissions.Companion.arrayListPermission
import com.example.travada.features.tabungan.form_two.FormTabunganTwoActivity
import com.example.travada.features.tabungan.MainTabungan.TabunganActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_form_tabungan_one.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*


class FormTabunganOneActivity : AppCompatActivity(),
    FormTabunganOnePresenter.Listener {

    private lateinit var presenter: FormTabunganOnePresenter
    lateinit var bitmapResult: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_tabungan_one)

        ivFormOneBack.setOnClickListener {
            val backToTabungan = Intent(this, TabunganActivity::class.java)
            startActivity(backToTabungan)
        }

        //TODO: btn image call button sheet
        ivImageTabungan.setOnClickListener {
            if (checkPermissions()) {
                val dialog = BottomSheetDialog(this)
                val view = layoutInflater.inflate(R.layout.camera_dialog_layout, null)
                val close = view.findViewById<Button>(R.id.btnBatal)
                val kamera = view.findViewById<TextView>(R.id.tvAmbilKamera)
                val galeri = view.findViewById<TextView>(R.id.tvAmbilGaleri)
                close.setOnClickListener {
                    dialog.dismiss()
                }

                kamera.setOnClickListener {
                    takePhotoFromCamera()
                    dialog.dismiss()
                }

                galeri.setOnClickListener {
                    choosePhotoFromGallery()
                    dialog.dismiss()
                }

                dialog.setCancelable(true)
                dialog.setContentView(view)
                dialog.show()


            } else {
                requestRequiredPermissions()
            }
        }

        ivEditImage.setOnClickListener {
            if (checkPermissions()) {
                val dialog = BottomSheetDialog(this)
                val view = layoutInflater.inflate(R.layout.camera_dialog_layout, null)
                val close = view.findViewById<Button>(R.id.btnBatal)
                val kamera = view.findViewById<TextView>(R.id.tvAmbilKamera)
                val galeri = view.findViewById<TextView>(R.id.tvAmbilGaleri)
                close.setOnClickListener {
                    dialog.dismiss()
                }

                kamera.setOnClickListener {
                    takePhotoFromCamera()
                    dialog.dismiss()
                }

                galeri.setOnClickListener {
                    choosePhotoFromGallery()
                    dialog.dismiss()
                }

                dialog.setCancelable(true)
                dialog.setContentView(view)
                dialog.show()
            } else {
                requestRequiredPermissions()
            }
        }

        presenter = FormTabunganOnePresenter(this)
        etTujuan.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(count: Editable?) {
                if (count?.length!! > layoutEtTujuan.counterMaxLength) {
                    layoutEtTujuan.error = "karakter lebih dari 25"
                } else {
                    layoutEtTujuan.error = null
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, count: Int) {
                presenter.checked(etTujuan.text.toString(), etJumlah.text.toString())

            }
        })


        etJumlah.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(count: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(etTujuan.text.toString(), etJumlah.text.toString())
            }
        })
    }

    //Mengecek semua permission yang dibutuhkan. Akan false jika setidaknya ada 1 permission yang belum mendapatkan izin.
    fun checkPermissions(): Boolean {
        return (
                (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) &&
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

    //Method yang menangani hasil / respon dari Implicit Intent ke Aplikasi Gallery maupun Aplikasi Camera
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Jika user memilih Gallery, menjalankan block ini.
        if (requestCode == GALLERY_REQUEST) {
            //Cek data hasil responnya tidak null
            if (data != null) {
                val contentUri = data.data
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, contentUri)
                Toast.makeText(this, "image loaded", Toast.LENGTH_SHORT).show()
                ivImageTabungan.setImageBitmap(bitmap)
                bitmapResult = bitmap
                ivEditImage.visibility = View.VISIBLE
                img_camera.visibility = View.GONE
                ivImageTabungan.isClickable = false
                tvImageMax.visibility = View.INVISIBLE

            } else {
                Toast.makeText(this, "Image Loading Failed", Toast.LENGTH_LONG).show()
            }
        } else if (requestCode == CAMERA_REQUEST) {
            val thumbnail = data?.extras?.get("data") as Bitmap
            ivImageTabungan.setImageBitmap(thumbnail)

            bitmapResult = thumbnail
            ivEditImage.visibility = View.VISIBLE
            img_camera.visibility = View.GONE
            ivImageTabungan.isClickable = false
            tvImageMax.visibility = View.INVISIBLE
        }
    }

    fun choosePhotoFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST)
    }

    fun takePhotoFromCamera() {
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intentCamera, CAMERA_REQUEST)
    }

    fun saveImage(bitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bytes)

        val directoryTarget =
            File((Environment.getExternalStorageDirectory()).toString() + "/PICTURES")
        Log.d("CH8", directoryTarget.toString())

        if (!directoryTarget.exists()) {
            directoryTarget.mkdirs()
        }

        val file = File(directoryTarget, ((Calendar.getInstance().timeInMillis).toString() + ".jpg"))

        file.createNewFile()

        val fileOutputStream = FileOutputStream(file)

        fileOutputStream.write(bytes.toByteArray())

        MediaScannerConnection.scanFile(this, arrayOf(file.path), arrayOf("images/jpg"), null)

        fileOutputStream.close()
        Log.d("CH8", "File saved in ${file.absolutePath}")

        return file.absolutePath
    }


    // TODO : tombol lanjut ke form berikutnya
    override fun btnActive() {
        btnLanjutFormOne.setBackgroundResource(R.drawable.bg_active)
        btnLanjutFormOne.setTextColor(Color.parseColor("#ffffff"))
        btnLanjutFormOne.setElevation(2f)
        btnLanjutFormOne.isClickable = true

        btnLanjutFormOne.setOnClickListener {
            val goToFormTabunganTwo = Intent(this, FormTabunganTwoActivity::class.java)
            startActivity(goToFormTabunganTwo)
        }
    }

    override fun btnInactive() {
        btnLanjutFormOne.setBackgroundResource(R.drawable.bg_inactive)
        btnLanjutFormOne.isClickable = false
    }


}