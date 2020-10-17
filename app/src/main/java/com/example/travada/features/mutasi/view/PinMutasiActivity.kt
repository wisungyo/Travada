package com.example.travada.features.mutasi.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.numpad.NumPadClick
import com.example.numpad.numPadClickListener
import com.example.travada.R
import com.example.travada.features.mutasi.presenter.PinMutasiActivityPresenter
import com.example.travada.features.rencana.searchpage.room.DatabaseItem
import com.example.travada.welcomepage.forgetpin.inputcode.ForgetpinInputCodeActivity
import com.example.travada.welcomepage.onboarding.OnboardingEndActivity
import kotlinx.android.synthetic.main.activity_login_pin.*
import kotlinx.android.synthetic.main.activity_login_pin.PinView
import kotlinx.android.synthetic.main.activity_login_pin.btn_logout
import kotlinx.android.synthetic.main.activity_login_pin.iv_image
import kotlinx.android.synthetic.main.activity_login_pin.numpad
import kotlinx.android.synthetic.main.activity_login_pin.tv_err
import kotlinx.android.synthetic.main.activity_regular_pin.*

class PinMutasiActivity : AppCompatActivity(), PinMutasiActivityPresenter.Listener {
    private lateinit var presenter: PinMutasiActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regular_pin)
        presenter = PinMutasiActivityPresenter(this)

        Glide
            .with(this)
            .load(R.drawable.image_login)
            .into(iv_image)

        numpad.setOnNumPadClickListener(NumPadClick(numPadClickListener { nums: ArrayList<Int> ->
            if (nums.size <= 6) {
                presenter.pinView(nums)
            } else {
                nums.removeAt(6)
            }

        }))

        btn_logout.setOnClickListener {
            DatabaseItem.getInstance(this)?.let {
                presenter.logout(it)
            }
        }

        iv_back.setOnClickListener {
            finish()
        }
    }

    override fun goToOnboardingEndActivity() {
        val intent = Intent(this, OnboardingEndActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun goToMainPageActivity() {
//        PinView.setTextColor(Color.parseColor(getString(R.color.greensuccess)))
        val intent = Intent(this, ResultMutasiActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun goToForgetPinActivity() {
        val intent = Intent(this, ForgetpinInputCodeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showErrorMessage(text: String) {
        tv_err.visibility = View.VISIBLE
        PinView.setTextColor(Color.parseColor(getString(R.color.rederror)))
        tv_err.text = text
    }

    override fun hideErrorMessage() {
        tv_err.visibility = View.INVISIBLE
        PinView.setTextColor(Color.parseColor(getString(R.color.blue)))
    }

    override fun setPinView(numb: String) {
        PinView.setText(numb)
    }
}