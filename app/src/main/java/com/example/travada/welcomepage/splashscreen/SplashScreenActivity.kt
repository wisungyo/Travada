package com.example.travada.welcomepage.splashscreen

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.travada.R
import com.example.travada.mainpage.MainPageActivity
import com.example.travada.util.util
import com.example.travada.welcomepage.loginpin.LoginPinActivity
import com.example.travada.welcomepage.onboarding.OnboardingActivity
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity(), SplashScreenPresenter.Listener {
    private lateinit var presenter: SplashScreenPresenter
//    lateinit var sharedPreferences: android.content.SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        presenter = SplashScreenPresenter(this)

//        sharedPreferences = getSharedPreferences(util.SF)



        Glide
            .with(this)
            .load(R.drawable.image_splashscreen)
            .into(iv_logo)

        Handler(Looper.getMainLooper()).postDelayed({
            presenter.checkIsLogin()
        }, 700)
    }


    override fun goToOnboarding() {
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun goToLoginPin() {
        val intent = Intent(this, LoginPinActivity::class.java)
        startActivity(intent)
        finish()
    }

}