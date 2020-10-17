package com.example.travada.features.transfer.transferbank

import android.os.Bundle

class TransferConfirmPresenter(val listener: Listener) {

    interface Listener {
        fun goToPinActivity(bundle: Bundle)
        fun setInfo(bundle: Bundle)
    }
}