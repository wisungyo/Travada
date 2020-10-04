package com.example.travada.features.tabungan.formtabungansatu

import android.os.Bundle

class FormTabunganOnePresenter(private val listener: Listener)  {
    fun checked(
        tujuan: String,
        jumlah: String,
        uriGambar: String
    ) {

        if (tujuan.isNotEmpty() && jumlah.isNotEmpty() && uriGambar.isNotEmpty()) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    fun nextPage() {
        listener.goToNextPage()
    }


    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun goToNextPage()
        fun errTujuan(message: String?)
        fun errJumlah(message: String?)
        fun goToUploadGambar()
        fun goToUpdateGambar()
    }
}