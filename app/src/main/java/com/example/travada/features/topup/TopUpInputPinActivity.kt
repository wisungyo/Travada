package com.example.travada.features.topup

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.numpad.NumPadClick
import com.example.numpad.numPadClickListener
import com.example.travada.R
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_top_up_input_pin.*


class TopUpInputPinActivity : AppCompatActivity(), TopUpInputPinPresenter.Listener {
    private lateinit var presenter: TopUpInputPinPresenter
    private lateinit var bundle: Bundle
    val MyFragment = LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_input_pin)
        presenter = TopUpInputPinPresenter(this)
        intent?.extras?.let { bundle = it }

        Glide
            .with(this)
            .load(R.drawable.ic_binar_big)
            .into(iv_image)

        btn_back.setOnClickListener { finish() }

        numpad.setOnNumPadClickListener(NumPadClick(numPadClickListener { nums:ArrayList<Int> ->
            if (nums.size <= 6) {
                presenter.pinView(nums, bundle)
            } else {
                nums.removeAt(6)
            }
        }))
    }

    override fun goToInvoiceActivity(bundle: Bundle) {
        val intent = Intent(this, TopUpInvoiceActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
        finishAffinity()
    }

    override fun setPinView(numb: String) {
        PinView.setText(numb)
    }

    override fun showToast(text: String) {
        Toast.makeText(
            this@TopUpInputPinActivity, text,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showLoadingDialog() {
        val fm = supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
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
}