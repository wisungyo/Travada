package com.example.travada.welcomepage.login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.fragmentnav.beranda.BerandaFragment
import com.example.travada.mainpage.MainPageActivity
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.welcomepage.forgetpass.inputemail.ForgetpassInputEmailActivity
import com.example.travada.welcomepage.onboarding.OnboardingEndActivity
import com.example.travada.welcomepage.register.register1.Register1Activity
import com.example.travada.welcomepage.register.register2.Register2Activity
import com.example.travada.welcomepage.splashscreen.SplashScreenActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btn_login
import kotlinx.android.synthetic.main.activity_login.iv_image
import kotlinx.android.synthetic.main.activity_login_pin.*
import kotlinx.android.synthetic.main.activity_onboarding_end.*
import kotlinx.android.synthetic.main.activity_splash_screen.*

class LoginActivity : AppCompatActivity(), LoginPresenter.Listener {
    private lateinit var presenter: LoginPresenter
    val MyFragment= LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)


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
            goToNextPage()
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
    }

    override fun showLoadingDialog() {
        val fm=supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun showErrorUsername(text: String) {
        til_username.error = text
    }

    override fun hideErrorUsername() {
        til_username.error = null
    }

    override fun showErrorPassword(text: String) {
        til_password.error = text
    }

    override fun hideErrorPassword() {
        til_password.error = null
    }

    companion object {
        var isError = false
    }
}