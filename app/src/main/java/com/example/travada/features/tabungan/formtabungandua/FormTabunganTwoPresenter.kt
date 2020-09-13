package com.example.travada.features.tabungan.formtabungandua

class FormTabunganTwoPresenter(private val listener: Listener) {

    fun checked(
        etTanggal: String,
        etSetoranAwal: String,
        etMetodeTabungan: String,
        etPeriodeTabungan: String,
        etJumlahTabungan: String
    ) {
        if (etTanggal.isNotEmpty() && etSetoranAwal.isNotEmpty() && etMetodeTabungan.isNotEmpty() && etPeriodeTabungan.isNotEmpty() && etJumlahTabungan.isNotEmpty()) {
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