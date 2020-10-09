package com.example.travada.features.transfer

class TransferMenuPresenter(val listener: Listener) {

    interface Listener {
        fun goToTransferActivity()
        fun goToVAActivity()
        fun btnActive()
        fun btnInactive()
        fun showToast(text:String)
    }
}