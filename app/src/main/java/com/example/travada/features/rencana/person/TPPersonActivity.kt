package com.example.travada.features.rencana.person

import android.Manifest
import android.R.attr
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64.encodeToString
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.travada.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_t_p_person.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*
import android.util.Base64


class TPPersonActivity : AppCompatActivity(), TPPersonPresenter.Listener {
    private lateinit var presenter: TPPersonPresenter
    //    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_t_p_person)
        isError = false
        uriKTP = ""
        uriPassport = ""

        //Bundle isinya Orang ke berapa
        val orang = intent.getIntExtra("ORANG", 1)

        //TODO : change name in action bar
        actionbar_title.text = "Orang ${orang + 1}"

        presenter = TPPersonPresenter(this)

        btn_back.setOnClickListener {
            finish()
        }

        btn_KTP.setOnClickListener {
            goToUploadKTP()
        }

        btn_Passport.setOnClickListener {
            goToUploadPassport()
        }

        et_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checkName(et_name.text.toString())
                presenter.checket(
                    et_name.text.toString(),
                    et_phone.text.toString(),
                    et_email.text.toString(),
                    uriKTP,
                    uriPassport
                )
            }
        })

        et_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checkEmail(et_email.text.toString())
                presenter.checket(
                    et_name.text.toString(),
                    et_phone.text.toString(),
                    et_email.text.toString(),
                    uriKTP,
                    uriPassport
                )
            }
        })

        et_phone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checkPhone(et_phone.text.toString())
                presenter.checket(
                    et_name.text.toString(),
                    et_phone.text.toString(),
                    et_email.text.toString(),
                    uriKTP,
                    uriPassport
                )
            }
        })

        btn_next.setOnClickListener {
            presenter.nextPage()
        }
    }

    override fun btnActive() {
        btn_next.setBackgroundResource(R.drawable.bg_btnactive)
        btn_next.setTextColor(Color.parseColor(getString(R.color.white)))
        btn_next.isClickable = true
    }

    override fun btnInactive() {
        btn_next.setBackgroundResource(R.drawable.bg_btninactive)
        btn_next.setTextColor(Color.parseColor(getString(R.color.gray)))
        btn_next.isClickable = false
    }

    override fun goToNextPage() {
        val orang = intent.getIntExtra("ORANG", 1)
        val returnIntent = Intent()
        returnIntent.putExtra("name", et_name.text.toString())
        returnIntent.putExtra("phone", et_phone.text.toString())
        returnIntent.putExtra("email", et_email.text.toString())
        returnIntent.putExtra("uriKTP", uriKTP)
        returnIntent.putExtra("uriPassport", uriPassport)
        returnIntent.putExtra("id", orang)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun errName(message: String?) {
        til_name.error = message
    }

    override fun errEmail(message: String?) {
        til_email.error = message
    }

    override fun errPhone(message: String?) {
        til_phone.error = message
    }

    override fun goToUploadKTP() {
        if (checkPermissions()) {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.camera_dialog_layout, null)

            val batal = view.findViewById<Button>(R.id.btnBatal)
            val kamera = view.findViewById<TextView>(R.id.tvAmbilKamera)
            val galeri = view.findViewById<TextView>(R.id.tvAmbilGaleri)

            batal.setOnClickListener {
                dialog.dismiss()
            }

            kamera.setOnClickListener {
                takePhotoFromCamera(CAMERA_REQUEST_KTP)
                dialog.dismiss()
            }

            galeri.setOnClickListener {
                choosePhotoFromGallery(GALLERY_REQUEST_KTP)
                dialog.dismiss()
            }

            dialog.setCancelable(true)
            dialog.setContentView(view)
            dialog.show()
        } else {
            requestRequiredPermissions()
        }
    }

    override fun goToUploadPassport() {
        if (checkPermissions()) {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.camera_dialog_layout, null)

            val batal = view.findViewById<Button>(R.id.btnBatal)
            val kamera = view.findViewById<TextView>(R.id.tvAmbilKamera)
            val galeri = view.findViewById<TextView>(R.id.tvAmbilGaleri)

            batal.setOnClickListener {
                dialog.dismiss()
            }

            kamera.setOnClickListener {
                takePhotoFromCamera(CAMERA_REQUEST_PASSPORT)
                dialog.dismiss()
            }

            galeri.setOnClickListener {
                choosePhotoFromGallery(GALLERY_REQUEST_PASSPORT)
                dialog.dismiss()
            }

            dialog.setCancelable(true)
            dialog.setContentView(view)
            dialog.show()
        } else {
            requestRequiredPermissions()
        }
    }

    fun choosePhotoFromGallery(code: Int) {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, code)
    }

    fun takePhotoFromCamera(code: Int) {
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intentCamera, code)
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    override fun showToast(text: String) {
        Toast.makeText(
            this@TPPersonActivity, text,
            Toast.LENGTH_LONG
        ).show()
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

    companion object {
        var isError: Boolean = false
        var uriKTP: String = ""
        var uriPassport: String = ""

        const val REQUEST_CODE = 209
        const val CAMERA_REQUEST_KTP = 1001
        const val GALLERY_REQUEST_KTP = 1002
        const val CAMERA_REQUEST_PASSPORT = 1003
        const val GALLERY_REQUEST_PASSPORT = 1004
        val arrayListPermission = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_KTP) {
            if (data != null) {
//                var bitmap = data?.extras?.get("data") as Bitmap
//
//                val baos = ByteArrayOutputStream()
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//                val imageBytes: ByteArray = baos.toByteArray()
//                val imageString: String = Base64.encodeToString(imageBytes, Base64.DEFAULT)
//                uriKTP = imageString
                uriKTP = data.data.toString()
//                val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
//                val imageFileName =
//                    SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())
//                        .toString()
//                val outputDirectory = getOutputDirectory()
//                val image: File = File(
//                    outputDirectory,
//                    imageFileName + ".jpg"
//                )
//
//                if (!image.exists()) {
//                    var fos: FileOutputStream? = null
//                    try {
//                        fos = FileOutputStream(image)
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
//                        fos.flush()
//                        fos.close()
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }
//                }
//
//                uriKTP = Uri.fromFile(image).toString()


                btn_KTP.setBackgroundResource(R.drawable.bg_btndone)
                btn_KTP.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_upload,
                    0,
                    R.drawable.ic_outline_check_circle_24,
                    0
                )

                presenter.checket(
                    et_name.text.toString(),
                    et_phone.text.toString(),
                    et_email.text.toString(),
                    uriKTP,
                    uriPassport
                )


            } else {
                showToast("Gagal memuat gambar")
            }
        } else if (requestCode == GALLERY_REQUEST_KTP) {
            if (data != null) {
                uriKTP = data.data.toString()
//                val bitmap =
//                    MediaStore.Images.Media.getBitmap(contentResolver, data.data)
//
//                val baos = ByteArrayOutputStream()
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//                val imageBytes: ByteArray = baos.toByteArray()
//                val imageString: String = Base64.encodeToString(imageBytes, Base64.DEFAULT)
//                uriKTP = imageString

                btn_KTP.setBackgroundResource(R.drawable.bg_btndone)
                btn_KTP.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_upload,
                    0,
                    R.drawable.ic_outline_check_circle_24,
                    0
                )
                presenter.checket(
                    et_name.text.toString(),
                    et_phone.text.toString(),
                    et_email.text.toString(),
                    uriKTP,
                    uriPassport
                )
            } else {
                showToast("Gagal memuat gambar")
            }
        } else if (requestCode == CAMERA_REQUEST_PASSPORT) {
            if (data != null) {
//                var bitmap = data?.extras?.get("data") as Bitmap
                uriPassport = data.data.toString()

//                val baos = ByteArrayOutputStream()
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//                val imageBytes: ByteArray = baos.toByteArray()
//                val imageString: String = Base64.encodeToString(imageBytes, Base64.DEFAULT)
//                uriPassport = imageString

                btn_Passport.setBackgroundResource(R.drawable.bg_btndone)
                btn_Passport.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_upload,
                    0,
                    R.drawable.ic_outline_check_circle_24,
                    0
                )
                presenter.checket(
                    et_name.text.toString(),
                    et_phone.text.toString(),
                    et_email.text.toString(),
                    uriKTP,
                    uriPassport
                )
            } else {
                showToast("Gagal memuat gambar")
            }
        } else if (requestCode == GALLERY_REQUEST_PASSPORT) {
            if (data != null) {
                uriPassport = data.data.toString()
//                val bitmap =
//                    MediaStore.Images.Media.getBitmap(contentResolver, data.data)
//
//                val baos = ByteArrayOutputStream()
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//                val imageBytes: ByteArray = baos.toByteArray()
//                val imageString: String = Base64.encodeToString(imageBytes, Base64.DEFAULT)
//                uriPassport = imageString

                btn_Passport.setBackgroundResource(R.drawable.bg_btndone)
                btn_Passport.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_upload,
                    0,
                    R.drawable.ic_outline_check_circle_24,
                    0
                )
                presenter.checket(
                    et_name.text.toString(),
                    et_phone.text.toString(),
                    et_email.text.toString(),
                    uriKTP,
                    uriPassport
                )
            } else {
                showToast("Gagal memuat gambar")
            }
        }
    }
}