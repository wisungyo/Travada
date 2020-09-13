package com.example.travada.features.tabungan.form_one

import kotlinx.android.synthetic.main.activity_form_tabungan_one.*

class FormTabunganOnePresenter(private val listener: Listener)  {

    fun checked(tujuan: String, jumlah: String) {
        if (tujuan.isNotEmpty() && jumlah.isNotEmpty()) {
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