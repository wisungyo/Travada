package com.example.travada.features.topup

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.travada.R
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.welcomepage.forgetpass.inputemail.ForgetpassInputEmailActivity
import kotlinx.android.synthetic.main.activity_register1.*
import kotlinx.android.synthetic.main.activity_top_up_input.*
import kotlinx.android.synthetic.main.activity_top_up_input.et_accountnumb
import kotlinx.android.synthetic.main.activity_top_up_input.til_accountnumb

class TopUpInputActivity : AppCompatActivity(), TopUpInputPresenter.Listener {
    private lateinit var presenter: TopUpInputPresenter
    val MyFragment= LoadingDialog()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_input)
        presenter = TopUpInputPresenter(this)

        presenter.showErrorAccountnumb()

        

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

    override fun goToNextPage() {
        val goToNextActivity = Intent(this, ForgetpassInputEmailActivity::class.java)
        val bundle = Bundle()
        bundle.putString("nominal", et_topup_nominal.text.toString())
        bundle.putString("accountnumb", et_accountnumb.text.toString())
        bundle.putString("note", et_email.text.toString())
        intent.putExtras(bundle)
        startActivity(goToNextActivity)
    }

    override fun showLoadingDialog() {
        val fm=supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun errAccountnumb(text: String?) {
        til_accountnumb.error = text
    }

    override fun showToast(text: String) {
        Toast.makeText(
            this@TopUpInputActivity, text,
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        var isError = false
    }
}