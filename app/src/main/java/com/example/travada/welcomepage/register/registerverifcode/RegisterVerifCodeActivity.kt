package com.example.travada.welcomepage.register.registerverifcode

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.mainpage.MainPageActivity
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.util.util
import kotlinx.android.synthetic.main.activity_register_verif_code.*




class RegisterVerifCodeActivity : AppCompatActivity(), RegisterVerifCodePresenter.Listener {
    private lateinit var presenter: RegisterVerifCodePresenter
    private lateinit var bundle: Bundle
    val LoadingDialog = LoadingDialog()
    val DoneDialog = com.example.travada.util.donedialog.DoneDialog()
    lateinit var sharedPreferences: android.content.SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_verif_code)

        intent?.extras?.let { bundle = it }
        presenter = RegisterVerifCodePresenter(this)
        sharedPreferences = getSharedPreferences(
            util.SF,
            Context.MODE_PRIVATE
        )

        Glide
            .with(this)
            .load(R.drawable.image_register_verif)
            .into(iv_image)

        presenter.startTimer()
        tv_email.text = bundle.getString("email")

        btn_next.setOnClickListener {
            presenter.checkVerif(bundle, firstPinView.text.toString(), sharedPreferences)
        }

        btn_back.setOnClickListener {
            finish()
        }

        tv_sendagain.setOnClickListener {
            presenter.sendAgain(bundle)
        }

        firstPinView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                hideErrorMessage()
                presenter.checket(firstPinView.text.toString())
            }
        })

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
        tv_error.visibility = VISIBLE
        firstPinView.setTextColor(Color.parseColor(getString(R.color.rederror)))
    }

    override fun hideErrorMessage() {
        tv_error.visibility = INVISIBLE
        firstPinView.setTextColor(Color.parseColor(getString(R.color.blue)))
    }

    override fun goToNextPage(bundle: Bundle) {
        val goToNextActivity = Intent(this, MainPageActivity::class.java)
        val fm=supportFragmentManager
        val argument = Bundle()
        argument.putString("text", "Akun berhasil dibuat")
        DoneDialog.setArguments(argument)
        DoneDialog.isCancelable = false
        DoneDialog.show(fm, "Fragment")

        Handler(Looper.getMainLooper()).postDelayed({
            DoneDialog.dismiss()
            startActivity(goToNextActivity)
            finishAffinity()
        }, 6000)

    }

    override fun showLoadingDialog() {
        val fm=supportFragmentManager
        LoadingDialog.isCancelable = false
        LoadingDialog.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        LoadingDialog.dismiss()
    }

    override fun showToast(text: String) {
        Toast.makeText(
            this@RegisterVerifCodeActivity, text,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showCountdown() {
        tv_sendagaincountdown.visibility = VISIBLE
        tv_sendagain.visibility = INVISIBLE
    }

    override fun hideCountdown() {
        tv_sendagaincountdown.visibility = INVISIBLE
        tv_sendagain.visibility = VISIBLE
    }

    override fun countdownTimerUI(count:Long) {
        tv_sendagaincountdown.text = "Kirim ulang kode(${count}s)"
    }


}