package com.example.travada.features.topup

import com.example.travada.features.topup.TopUpInputActivity.Companion.isError
import com.example.travada.welcomepage.login.LoginActivity

class TopUpInputPresenter(val listener:Listener) {

    fun showErrorAccountnumb(){
        if (isError == true ){
            listener.errAccountnumb("Nomor rekening yang dimasukkan salah")
        }
    }

    fun checket(nominal: String, accountnum: String) {
        if (nominal.isNotEmpty() && accountnum.isNotEmpty() && isError == false) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }


    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun goToNextPage()
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun errAccountnumb(text: String?)
        fun showToast(text:String)
    }
}