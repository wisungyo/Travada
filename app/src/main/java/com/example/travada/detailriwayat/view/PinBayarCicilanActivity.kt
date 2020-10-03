package com.example.travada.detailriwayat.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.numpad.NumPadClick
import com.example.numpad.numPadClickListener
import com.example.travada.R
import com.example.travada.detailriwayat.presenter.PinBayarCicilanActivityPresenter
import com.example.travada.features.rencana.searchpage.room.DatabaseItem
import com.example.travada.welcomepage.forgetpin.inputcode.ForgetpinInputCodeActivity
import com.example.travada.welcomepage.onboarding.OnboardingEndActivity
import kotlinx.android.synthetic.main.activity_login_pin.*

class PinBayarCicilanActivity : AppCompatActivity(), PinBayarCicilanActivityPresenter.Listener {
    private lateinit var presenter: PinBayarCicilanActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_pin)
        presenter = PinBayarCicilanActivityPresenter(this)

        Glide
            .with(this)
            .load(R.drawable.image_login)
            .into(iv_image)

        numpad.setOnNumPadClickListener(NumPadClick(numPadClickListener { nums: ArrayList<Int> ->
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
//        val intent = Intent(this, DetailRiwayatActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
//        startActivity(intent)
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