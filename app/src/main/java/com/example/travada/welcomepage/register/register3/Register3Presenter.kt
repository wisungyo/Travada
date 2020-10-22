package com.example.travada.welcomepage.register.register3

import android.os.Bundle

class Register3Presenter(val listener: Listener) {


    fun checket(password:String, verifPassword:String) {
        if (password.length >= 8 && verifPassword.length >= 8 ) {
            listener.showErrorMessage()
            if (password == verifPassword){
                listener.btnActive()
                listener.hideErrorMessage()
            }
        } else {
            listener.btnInactive()
        }
    }

    fun goToNextPage(bundle: Bundle) {
        listener.goToNextPage(bundle)
    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun showErrorMessage()
        fun hideErrorMessage()
        fun goToNextPage(bundle: Bundle)
        fun btnBack()
    }
}