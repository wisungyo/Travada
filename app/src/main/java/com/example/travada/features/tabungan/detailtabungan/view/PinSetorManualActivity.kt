package com.example.travada.features.tabungan.detailtabungan.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.numpad.NumPadClick
import com.example.numpad.numPadClickListener
import com.example.travada.R
import com.example.travada.features.tabungan.detailtabungan.presenter.PinSetorManualPresenter
import kotlinx.android.synthetic.main.activity_login_pin.PinView
import kotlinx.android.synthetic.main.activity_login_pin.iv_image
import kotlinx.android.synthetic.main.activity_login_pin.numpad
import kotlinx.android.synthetic.main.activity_login_pin.tv_err
import kotlinx.android.synthetic.main.activity_transfer_pin.*

class PinSetorManualActivity : AppCompatActivity(),
    PinSetorManualPresenter.Listener {
    lateinit var presenter: PinSetorManualPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_setor_manual)

        presenter =
            PinSetorManualPresenter(
                this
            )

        Glide
            .with(this)
            .load(R.drawable.image_login)
            .into(iv_image)

        numpad.setOnNumPadClickListener(NumPadClick(numPadClickListener { nums: ArrayList<Int> ->
            presenter.pinView(nums)
        }))

        btn_back.setOnClickListener {
            finish()
        }
    }

    override fun goToInvoiceActivity() {
        val intent = Intent(this, DetailTabunganActivity::class.java)
        startActivity(intent)
        finish()
    }

    @SuppressLint("ResourceType")
    override fun showErrorMessage(text: String) {
        tv_err.visibility = View.VISIBLE
        PinView.setTextColor(Color.parseColor(getString(R.color.rederror)))
        tv_err.text = text
    }

    @SuppressLint("ResourceType")
    override fun hideErrorMessage() {
        tv_err.visibility = View.INVISIBLE
        PinView.setTextColor(Color.parseColor(getString(R.color.blue)))

    }

    override fun setPinView(numb: String) {
        PinView.setText(numb)
    }

}
