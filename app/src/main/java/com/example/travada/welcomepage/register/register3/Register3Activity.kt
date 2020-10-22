package com.example.travada.welcomepage.register.register3

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.welcomepage.register.register4.Register4Activity
import kotlinx.android.synthetic.main.activity_register3.*

class Register3Activity : AppCompatActivity(), Register3Presenter.Listener {
    private lateinit var presenter: Register3Presenter
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register3)

        intent?.extras?.let { bundle = it }
        presenter = Register3Presenter(this)

        Glide
            .with(this)
            .load(R.drawable.image_register3)
            .into(statusbar)

        et_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checket(
                    et_password.text.toString(),
                    et_verifpassword.text.toString()
                )
            }
        })

        et_verifpassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checket(
                    et_password.text.toString(),
                    et_verifpassword.text.toString()
                )
            }
        })

        btn_back.setOnClickListener {
            finish()
        }

        btn_next.setOnClickListener {
            presenter.goToNextPage(bundle)
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

    override fun showErrorMessage() {
        til_verifpassword.error = "Password dan Konfirmasi password harus sama"
    }

    override fun hideErrorMessage() {
        til_verifpassword.error = null
    }

    override fun goToNextPage(bundle: Bundle) {
        val intent = Intent(this, Register4Activity::class.java)
        bundle.putString("password", et_password.text.toString())
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun btnBack() {
        finish()
    }
}