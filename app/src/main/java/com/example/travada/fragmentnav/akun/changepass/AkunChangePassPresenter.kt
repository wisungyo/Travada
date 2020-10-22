package com.example.travada.fragmentnav.akun.changepass

import android.os.Bundle

class AkunChangePassPresenter(val listener: Listener) {

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