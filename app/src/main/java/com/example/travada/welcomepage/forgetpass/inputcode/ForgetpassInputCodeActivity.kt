package com.example.travada.welcomepage.forgetpass.inputcode

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.welcomepage.forgetpass.inputnewpass.ForgetpassInputNewPassActivity
import kotlinx.android.synthetic.main.activity_forgetpass_input_code.*


class ForgetpassInputCodeActivity : AppCompatActivity(), ForgetpassInputCodePresenter.Listener {
    private lateinit var presenter: ForgetpassInputCodePresenter
    private lateinit var bundle: Bundle
    val MyFragment = LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetpass_input_code)
        presenter = ForgetpassInputCodePresenter(this)
        intent?.extras?.let { bundle = it }

        Glide
            .with(this)
            .load(R.drawable.ic_image_email)
            .into(iv_image)

        presenter.startTimer()
        tv_email.text = bundle.getString("email")

        btn_back.setOnClickListener {
            finish()
        }



        btnInactive()
    }

    override fun btnActive() {
        btn_next.setBackgroundResource(R.drawable.bg_btnactive)
        btn_next.setTextColor(Color.parseColor(getString(R.color.white)))
        btn_next.isClickable = true
    }

    override fun btnInactive() {
        btn_next.setBackgroundResource(R.drawable.bg_btninactive)
        btn_next.setTextColor(Color.parseColor(getString(R.color.gray)))
        btn_next.isClickable = false
    }

    override fun ShowErrorMessage() {
        tv_error.visibility = View.VISIBLE

    }

    override fun hideErrorMessage() {
        tv_error.visibility = View.INVISIBLE
    }

    override fun goToNextPage(bundle: Bundle) {
        val intent = Intent(this, ForgetpassInputNewPassActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun showLoadingDialog() {
        val fm = supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun showToast(text: String) {
        Toast.makeText(
            this@ForgetpassInputCodeActivity, text,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showCountdown() {
        tv_sendagaincountdown.visibility = View.VISIBLE
        tv_sendagain.visibility = View.INVISIBLE
    }

    override fun hideCountdown() {
        tv_sendagaincountdown.visibility = View.INVISIBLE
        tv_sendagain.visibility = View.VISIBLE
    }

    override fun countdownTimerUI(count: Long) {
        tv_sendagaincountdown.text = "Kirim ulang kode(${count}s)"
    }
}