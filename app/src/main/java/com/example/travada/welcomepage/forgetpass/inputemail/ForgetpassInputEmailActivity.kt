package com.example.travada.welcomepage.forgetpass.inputemail

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.welcomepage.forgetpass.inputcode.ForgetpassInputCodeActivity
import com.example.travada.welcomepage.forgetpass.inputcode.ForgetpassInputCodePresenter
import com.example.travada.welcomepage.register.register2.Register2Activity
import kotlinx.android.synthetic.main.activity_forgetpass_input_email.*
import kotlinx.android.synthetic.main.activity_forgetpass_input_email.btn_back
import kotlinx.android.synthetic.main.activity_forgetpass_input_email.et_email
import kotlinx.android.synthetic.main.activity_forgetpass_input_email.til_email
import kotlinx.android.synthetic.main.activity_register1.*


class ForgetpassInputEmailActivity : AppCompatActivity(), ForgetpassInputEmailPresenter.Listener {
    private lateinit var presenter: ForgetpassInputEmailPresenter
    val MyFragment= LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetpass_input_email)
        presenter = ForgetpassInputEmailPresenter(this)

        Glide
            .with(this)
            .load(R.drawable.ic_image_email)
            .into(iv_image)

        et_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                hideErrorMessage()
                presenter.checkEmail(et_email.text.toString())
                presenter.checket(et_email.text.toString())
            }
        })

        btn_back.setOnClickListener {
            finish()
        }

        btn_send.setOnClickListener {

        }

        btnInactive()
    }

    override fun btnActive() {
        btn_send.setBackgroundResource(R.drawable.bg_btnactive)
        btn_send.setTextColor(Color.parseColor(getString(R.color.white)))
        btn_send.isClickable = true
    }

    override fun btnInactive() {
        btn_send.setBackgroundResource(R.drawable.bg_btninactive)
        btn_send.setTextColor(Color.parseColor(getString(R.color.gray)))
        btn_send.isClickable = false
    }

    override fun ShowErrorMessage(text: String?) {
        til_email.error = text
    }


    override fun hideErrorMessage() {
        til_email.error = null
    }

    override fun goToNextPage(bundle: Bundle) {
        val intent = Intent(this, ForgetpassInputCodeActivity::class.java)
        val bundle = Bundle()
        bundle.putString("email", et_email.text.toString())
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun showLoadingDialog() {
        val fm=supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun showToast(text: String) {
        Toast.makeText(
            this@ForgetpassInputEmailActivity, text,
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        var isError = false
    }
}