package com.example.travada.welcomepage.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.welcomepage.forgetpass.inputcode.ForgetpassInputCodeActivity
import com.example.travada.welcomepage.login.LoginActivity
import com.example.travada.welcomepage.loginpin.LoginPinActivity
import com.example.travada.welcomepage.register.register1.Register1Activity
import com.example.travada.welcomepage.register.register2.Register2Activity
import kotlinx.android.synthetic.main.activity_onboarding_end.*
import kotlinx.android.synthetic.main.activity_splash_screen.*

class OnboardingEndActivity : AppCompatActivity(), OnboardingEndPresenter.Listener {
    private lateinit var presenter:OnboardingEndPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_end)

        presenter = OnboardingEndPresenter(this)

        Glide
            .with(this)
            .load(R.drawable.ic_onboardingend)
            .into(iv_image)

        btn_login.setOnClickListener {
            presenter.goToLoginPage()
        }

        btn_register.setOnClickListener {
            presenter.goToRegisterPage()
        }

//        iv_image.setOnClickListener {
//
//                val intent = Intent(this, LoginPinActivity::class.java)
//                startActivity(intent)
//
//        }
    }

    override fun goToLoginPage() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun goToRegisterPage() {
        val intent = Intent(this, Register1Activity::class.java)
        startActivity(intent)
    }
}