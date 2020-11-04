package com.example.travada.welcomepage.forgetpass.inputnewpass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R

class ForgetpassInputNewPassActivity : AppCompatActivity(), ForgetpassInputNewPassPresenter.Listener {
    private lateinit var presenter: ForgetpassInputNewPassPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetpass_input_new_pass)
        presenter = ForgetpassInputNewPassPresenter(this)

    }

    override fun btnActive() {
        TODO("Not yet implemented")
    }

    override fun btnInactive() {
        TODO("Not yet implemented")
    }

    override fun ShowErrorMessage() {
        TODO("Not yet implemented")
    }

    override fun hideErrorMessage() {
        TODO("Not yet implemented")
    }

    override fun goToNextPage(bundle: Bundle) {
        TODO("Not yet implemented")
    }

    override fun showLoadingDialog() {
        TODO("Not yet implemented")
    }

    override fun hideLoadingDialog() {
        TODO("Not yet implemented")
    }

    override fun showToast(text: String) {
        TODO("Not yet implemented")
    }
}