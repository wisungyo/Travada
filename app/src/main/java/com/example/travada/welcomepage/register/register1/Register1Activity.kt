package com.example.travada.welcomepage.register.register1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.welcomepage.register.register2.Register2Activity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_register1.*
import kotlinx.android.synthetic.main.activity_register1.et_username

class Register1Activity : AppCompatActivity(), Register1Presenter.Listener {
    private lateinit var presenter: Register1Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register1)

        presenter = Register1Presenter(this)

        Glide
            .with(this)
            .load(R.drawable.image_register1)
            .into(statusbar)

        et_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checket(
                    et_name.text.toString(),
                    et_email.text.toString(),
                    et_username.text.toString(),
                    et_phone.text.toString(),
                    et_accountnumb.text.toString()
                )


            }
        })

        et_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checkemail(et_email.text.toString(), til_email)
                presenter.checket(
                    et_name.text.toString(),
                    et_email.text.toString(),
                    et_username.text.toString(),
                    et_phone.text.toString(),
                    et_accountnumb.text.toString()
                )
            }
        })

        et_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checkusername(et_username.text.toString(), til_username)
                presenter.checket(
                    et_name.text.toString(),
                    et_email.text.toString(),
                    et_username.text.toString(),
                    et_phone.text.toString(),
                    et_accountnumb.text.toString()
                )
            }
        })

        et_phone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checkphone(et_phone.text.toString(), til_phone)
                presenter.checket(
                    et_name.text.toString(),
                    et_email.text.toString(),
                    et_username.text.toString(),
                    et_phone.text.toString(),
                    et_accountnumb.text.toString()
                )
            }
        })

        et_accountnumb.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checket(
                    et_name.text.toString(),
                    et_email.text.toString(),
                    et_username.text.toString(),
                    et_phone.text.toString(),
                    et_accountnumb.text.toString()
                )

            }
        })

        btn_next.setOnClickListener {
            presenter.goToNextPage()
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

    override fun goToNextPage() {
        val intent = Intent(this, Register2Activity::class.java)
        val bundle = Bundle()
        bundle.putString("name", et_name.text.toString())
        bundle.putString("email", et_email.text.toString())
        bundle.putString("username", et_username.text.toString())
        bundle.putString("phone", et_phone.text.toString())
        bundle.putString("accountnumb", et_accountnumb.text.toString())
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun btnBack() {
        finish()
    }

    override fun showErrorMessage(layout: TextInputLayout, message: String) {
        layout.error = message
        isError = true
    }

    override fun hideErrorMessage(layout: TextInputLayout) {
        layout.error = null
        isError = false
    }

    companion object {
        var isError: Boolean = false
    }
}