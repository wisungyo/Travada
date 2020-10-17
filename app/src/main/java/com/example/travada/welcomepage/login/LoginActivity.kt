package com.example.travada.welcomepage.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.mainpage.MainPageActivity
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.welcomepage.forgetpass.inputemail.ForgetpassInputEmailActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginPresenter.Listener {
    private lateinit var presenter: LoginPresenter
    val MyFragment= LoadingDialog()
//    lateinit var sharedPreferences: android.content.SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)
//        sharedPreferences = getSharedPreferences(
//            util.SF,
//            Context.MODE_PRIVATE
//        )


        Glide
            .with(this)
            .load(R.drawable.image_login)
            .into(iv_image)

        et_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                hideErrorPassword()
                presenter.checkpass(et_password.text.toString())
                presenter.checket(et_username.text.toString(), et_password.text.toString())
            }
        })

        et_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                hideErrorUsername()
                presenter.checkusername(et_username.text.toString())
                presenter.checket(et_username.text.toString(), et_password.text.toString())
            }
        })

        tv_toforgetpass.setOnClickListener {
                presenter.goToForgetPassPage()
        }

        btn_login.setOnClickListener {
            presenter.loginCheck(et_username.text.toString(), et_password.text.toString())
        }

    }


    override fun btnActive() {
        btn_login.setBackgroundResource(R.drawable.bg_btnactive)
        btn_login.setTextColor(Color.parseColor(getString(R.color.white)))
        btn_login.isClickable = true
    }

    override fun btnInactive() {
        btn_login.setBackgroundResource(R.drawable.bg_btninactive)
        btn_login.setTextColor(Color.parseColor(getString(R.color.gray)))
        btn_login.isClickable = false
    }

    override fun goToNextPage() {
        val goToNextActivity = Intent(this, MainPageActivity::class.java)
        startActivity(goToNextActivity)
    }

    override fun goToForgotPassPage() {
        val goToNextActivity = Intent(this, ForgetpassInputEmailActivity::class.java)
        startActivity(goToNextActivity)
        finish()
    }

    override fun showLoadingDialog() {
        val fm=supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun showErrorUsername(text: String?) {
        til_username.error = text
    }

    override fun hideErrorUsername() {
        til_username.error = null
    }

    override fun showErrorPassword(text: String?) {
        til_password.error = text
    }

    override fun hideErrorPassword() {
        til_password.error = null
    }

    override fun showToast(text: String) {
        Toast.makeText(
            this@LoginActivity, text,
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        var isError = false
    }
}