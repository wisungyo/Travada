package com.example.travada.features.tabungan.form_three

class FormTabunganThreePresenter(private val listener: Listener)  {

    fun checked(etNomorRekening: String) {
        if (etNomorRekening.isNotEmpty()) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
    }
}