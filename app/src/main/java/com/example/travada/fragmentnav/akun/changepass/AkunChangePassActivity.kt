package com.example.travada.fragmentnav.akun.changepass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R

class AkunChangePassActivity : AppCompatActivity(), AkunChangePassPresenter.Listener {
    private lateinit var presenter : AkunChangePassPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun_change_pass)

        presenter = AkunChangePassPresenter(this)

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