package com.example.travada.features.transfer.transferbank

class TransferInputPresenter(val listener: Listener) {

    interface Listener{
        fun btnActive()
        fun btnInactive()
        fun goToNextPage()
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun errAccountnumb(text: String?)
        fun errTransfernominal(text: String?)
        fun showToast(text:String)
    }
}