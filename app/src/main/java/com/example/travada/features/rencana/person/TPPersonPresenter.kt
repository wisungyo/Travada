package com.example.travada.features.rencana.person

import android.util.Patterns


class TPPersonPresenter(val listener: TPPersonPresenter.Listener) {

    fun checket(
        name: String,
        phone: String,
        email: String,
        uriKTP: String,
        uriPassport: String
    ) {
        if (name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && uriKTP.isNotEmpty() && uriPassport.isNotEmpty() && TPPersonActivity.isError == false
        ) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    fun checkName(text: String) {
        if (text.isNotEmpty()) {
            listener.errName(null)
            TPPersonActivity.isError = false
        } else {
            listener.errName("Nama tidak boleh kosong")
            TPPersonActivity.isError = true
        }
    }

    fun checkPhone(text: String) {
        if (text.length <= 9) {
            listener.errPhone("Nomer handphone minimal 10 karakter")
            TPPersonActivity.isError = true
        } else if (text.length > 12) {
            listener.errPhone("Nomer handphone maksimal 13 karakter")
            TPPersonActivity.isError = true
        } else {
            listener.errPhone(null)
            TPPersonActivity.isError = false
        }
    }

    fun checkEmail(text: String) {
        if (Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            listener.errEmail(null)
            TPPersonActivity.isError = false
        } else {
            listener.errEmail("Format email tidak valid")
            TPPersonActivity.isError = true
        }
    }

    fun nextPage() {
        listener.goToNextPage()
    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun goToNextPage()
        fun errName(message: String?)
        fun errEmail(message: String?)
        fun errPhone(message: String?)
        fun goToUploadKTP()
        fun goToUploadPassport()
        fun showToast(text: String)
    }
}