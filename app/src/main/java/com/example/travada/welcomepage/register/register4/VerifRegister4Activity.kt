package com.example.travada.welcomepage.register.register4

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.Typeface.create
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.welcomepage.login.LoginActivity
import com.example.travada.welcomepage.register.registerverifcode.RegisterVerifCodeActivity
import kotlinx.android.synthetic.main.activity_verif_register4.*

class VerifRegister4Activity : AppCompatActivity(), VerifRegister4Presenter.Listener {
    private lateinit var presenter: VerifRegister4Presenter
    private lateinit var bundle: Bundle
    val MyFragment = LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verif_register4)

        intent?.extras?.let { bundle = it }
        presenter = VerifRegister4Presenter(this)

        Glide
            .with(this)
            .load(R.drawable.image_register4)
            .into(statusbar)

        PinView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checket(
                    PinView.text.toString(),
                    bundle.getString("pin")
                )
            }
        })
//
//        val sourceKTP: ImageDecoder.Source =
//            ImageDecoder.create(this.getContentResolver(), bundle?.getString("uriKTP"))
//
//
//        val sourceSelfieKTP: ImageDecoder.Source =
//            ImageDecoder.createSource(this.getContentResolver(), bundle?.getString("uriSelfieKTP"))
//
//        val bitmapKTP: Bitmap = ImageDecoder.decodeBitmap(sourceKTP)
//        val bitmapSelfieKTP: Bitmap = ImageDecoder.decodeBitmap(sourceSelfieKTP)

        val bitmapKTP: Bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(bundle.getString("uriKTP")))
        val bitmapSelfieKTP: Bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(bundle.getString("uriSelfieKTP")))

        btn_next.setOnClickListener {
            presenter.chekRegisterFinal(bundle, bitmapKTP, bitmapSelfieKTP)
        }

        btn_back.setOnClickListener {
            finish()
        }

        btnInactive()
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

    override fun ShowErrorMessage() {
        tv_err.visibility = VISIBLE
        PinView.setTextColor(Color.parseColor(getString(R.color.rederror)))
    }

    override fun hideErrorMessage() {
        tv_err.visibility = INVISIBLE
        PinView.setTextColor(Color.parseColor(getString(R.color.blue)))
    }

    override fun goToNextPage(bundle: Bundle) {
        val intent = Intent(this, RegisterVerifCodeActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
        finishAffinity()
    }

    override fun showLoadingDialog() {
        val fm = supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun showToast(text: String) {
        Toast.makeText(
            this@VerifRegister4Activity, text,
            Toast.LENGTH_LONG
        ).show()
    }
}