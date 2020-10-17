package com.example.travada.features.transfer.va

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.numpad.NumPadClick
import com.example.numpad.numPadClickListener
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_v_a_pin.*


class VAPinActivity : AppCompatActivity(), VAPinPresenter.Listener {
    private lateinit var presenter: VAPinPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_v_a_pin)
        presenter = VAPinPresenter(this)

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
        val intent = Intent(this, VAInvoiceActivity::class.java)
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