package com.example.travada.welcomepage.loginpin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.numpad.NumPadClick
import com.example.numpad.numPadClickListener
import com.example.travada.R
import com.example.travada.features.rencana.searchpage.room.DatabaseItem
import com.example.travada.mainpage.MainPageActivity
import com.example.travada.welcomepage.forgetpin.inputcode.ForgetpinInputCodeActivity
import com.example.travada.welcomepage.onboarding.OnboardingEndActivity
import kotlinx.android.synthetic.main.activity_login_pin.*
import kotlinx.android.synthetic.main.activity_login_pin.PinView
import kotlinx.android.synthetic.main.activity_login_pin.tv_err


class LoginPinActivity : AppCompatActivity(), LoginPinPresenter.Listener {
    private lateinit var presenter: LoginPinPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_pin)
        presenter = LoginPinPresenter(this)

        Glide
            .with(this)
            .load(R.drawable.image_login)
            .into(iv_image)

        numpad.setOnNumPadClickListener(NumPadClick(numPadClickListener { nums:ArrayList<Int> ->
                presenter.pinView(nums)

        }))

        btn_logout.setOnClickListener {
            DatabaseItem.getInstance(this)?.let {
                presenter.logout(it)
            }
        }


    }

    override fun goToOnboardingEndActivity() {
        val intent = Intent(this, OnboardingEndActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun goToMainPageActivity() {
        val intent = Intent(this, MainPageActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun goToForgetPinActivity() {
        val intent = Intent(this, ForgetpinInputCodeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showErrorMessage(text: String) {
        tv_err.visibility = VISIBLE
        PinView.setTextColor(Color.parseColor(getString(R.color.rederror)))
        tv_err.text = text
    }

    override fun hideErrorMessage() {
        tv_err.visibility = INVISIBLE
        PinView.setTextColor(Color.parseColor(getString(R.color.blue)))
    }

    override fun setPinView(numb: String) {
        PinView.setText(numb)
    }
}