package com.example.travada.welcomepage.register.register4

import android.os.Bundle

class VerifRegister4Presenter(val listener: Listener) {

    fun checket(verifpin: String, pin: String?) {
        if (verifpin.length == 6) {
            if (verifpin == pin) {
                listener.btnActive()
                listener.hideErrorMessage()
            } else {
                listener.ShowErrorMessage()
            }
        } else {
            listener.btnInactive()
            listener.hideErrorMessage()
        }
    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun ShowErrorMessage()
        fun hideErrorMessage()
        fun goToNextPage()
    }
}