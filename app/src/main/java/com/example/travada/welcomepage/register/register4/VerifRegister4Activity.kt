package com.example.travada.welcomepage.register.register4

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.welcomepage.login.LoginActivity
import kotlinx.android.synthetic.main.activity_verif_register4.*

class VerifRegister4Activity : AppCompatActivity(), VerifRegister4Presenter.Listener {
    private lateinit var presenter: VerifRegister4Presenter
    private lateinit var bundle: Bundle

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

    override fun goToNextPage() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}