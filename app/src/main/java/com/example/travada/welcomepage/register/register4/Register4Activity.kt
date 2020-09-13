package com.example.travada.welcomepage.register.register4

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.bumptech.glide.Glide
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_register4.*

class Register4Activity : AppCompatActivity(), Register4Presenter.Listener {
    private lateinit var presenter: Register4Presenter
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register4)

        intent?.extras?.let { bundle = it }
        presenter = Register4Presenter(this)

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
                    PinView.text.toString()
                )
            }
        })

        btn_next.setOnClickListener {
            presenter.goToNextPage(bundle)
        }

        btn_back.setOnClickListener {
            finish()
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

    override fun goToNextPage(bundle: Bundle) {
        val intent = Intent(this, VerifRegister4Activity::class.java)
        bundle.putString("pin", PinView.text.toString())
        intent.putExtras(bundle)
        startActivity(intent)
    }
}