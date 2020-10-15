package com.example.travada.welcomepage.forgetpass.inputnewpass

import android.os.Bundle

class ForgetpassInputNewPassPresenter(val listener:Listener) {


    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun ShowErrorMessage()
        fun hideErrorMessage()
        fun goToNextPage(bundle: Bundle)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showToast(text: String)
    }
}