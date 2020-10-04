package com.example.travada.features.tabungan.formtabungandua

import android.os.Bundle
import com.example.travada.features.tabungan.adapter.BarengTemanAdapter
import com.example.travada.features.tabungan.adapter.ListWisataAdapter
import com.example.travada.features.tabungan.models.DataTabungBareng

class FormTabunganTwoPresenter(private val listener: Listener) {

    fun fetchTabungBarengData(){
         val listTabungBareng = arrayListOf(
            DataTabungBareng(
                "Nanda Adi",
                "1212131",
                "AN"
            ),
            DataTabungBareng(
                "Abigail",
                "1212122",
                "A"
            ),
            DataTabungBareng(
                "Nicholas",
                "3434343",
                "N"
            )
        )
        val adapterTabungBareng = BarengTemanAdapter(listTabungBareng)
        listener.showDataTabungBareng(adapterTabungBareng)

    }

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

    fun goToNextPage(bundle: Bundle) {
        listener.goToNextPage(bundle)
    }

    interface Listener {
        fun btnActive()
        fun goToNextPage(bundle: Bundle)
        fun btnInactive()
        fun showDataTabungBareng(adapterTabungBareng: BarengTemanAdapter)
    }
}