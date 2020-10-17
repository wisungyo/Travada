package com.example.travada.features.topup

import android.os.Bundle

class TopUpInvoicePresenter(val listener:Listener) {

    interface Listener {
        fun goToMainPage()
        fun setInformation(bundle: Bundle)
    }
}