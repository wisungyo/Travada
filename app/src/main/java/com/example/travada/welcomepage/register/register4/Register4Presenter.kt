package com.example.travada.welcomepage.register.register4

import android.os.Bundle
import com.example.travada.welcomepage.register.register3.Register3Presenter

class Register4Presenter(val listener: Listener) {

    fun checket(pin: String) {
        if (pin.length == 6) {
            listener.btnActive()

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
        fun goToNextPage(bundle: Bundle)
    }
}