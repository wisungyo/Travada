package com.example.travada.welcomepage.register.register1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.welcomepage.register.register2.Register2Activity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_register1.*
import kotlinx.android.synthetic.main.activity_register1.et_username
import java.lang.reflect.Array.getInt

class Register1Activity : AppCompatActivity(), Register1Presenter.Listener {
    private lateinit var presenter: Register1Presenter
    val MyFragment = LoadingDialog()

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
                presenter.checkName(et_name.text.toString())
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
                errEmail(null)
                presenter.checkEmail(et_email.text.toString())
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
                errUsername(null)
                presenter.checkUsername(et_username.text.toString())
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
                errPhone(null)
                presenter.checkPhone(et_phone.text.toString())
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
                errAccountnumb(null)
                presenter.checkAccountnumb(et_accountnumb.text.toString())
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
            presenter.checkData(
                et_email.text.toString(),
                et_phone.text.toString(),
                et_accountnumb.text.toString(),
                et_username.text.toString()

            )
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

    override fun errName(message: String?) {
        til_name.error = message
    }

    override fun errEmail(message: String?) {
        til_email.error = message
    }

    override fun errUsername(message: String?) {
        til_username.error = message
    }

    override fun errPhone(message: String?) {
        til_phone.error = message
    }

    override fun errAccountnumb(message: String?) {
        til_accountnumb.error = message
    }

    override fun showLoadingDialog() {
        val fm = supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun showToast(text:String) {
        Toast.makeText(
            this@Register1Activity, text,
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        var isError: Boolean = false
    }
}