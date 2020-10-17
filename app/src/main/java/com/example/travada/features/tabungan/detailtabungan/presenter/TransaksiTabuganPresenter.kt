package com.example.travada.features.tabungan.detailtabungan.presenter

import com.example.travada.features.tabungan.adapter.BulanAdapter
import com.example.travada.features.tabungan.models.DataBulan
import com.example.travada.features.tabungan.models.DataTransaksi

class TransaksiTabuganPresenter (val listener : Listener){

    fun fetchData(){
        val transaksiBulanJuni = arrayListOf(
            DataTransaksi("AA","Simpanan","Tabungan","Rp 100.000","3 Juni 2020"),
            DataTransaksi("BB","Simpanan","Tabungan","Rp 100.000","4 Juni 2020")
        )

        val transaksiBulanJuli = arrayListOf(
            DataTransaksi("CC","Simpanan","Tabungan","Rp 100.000","3 Juli 2020"),
            DataTransaksi("DD","Simpanan","Tabungan","Rp 100.000","4 Juli 2020"),
            DataTransaksi("EE","Simpanan","Tabungan","Rp 100.000","5 Juli 2020")
        )

        val transaksiBulanAgustus = arrayListOf(
            DataTransaksi("FF","Simpanan","Tabungan","Rp 100.000","3 Agustus 2020"),
            DataTransaksi("GG","Simpanan","Tabungan","Rp 100.000","4 Agustus 2020"),
            DataTransaksi("HH","Simpanan","Tabungan","Rp 100.000","5 Agustus 2020")
        )

        val transaksiBulanSeptember = arrayListOf(
            DataTransaksi("OO","Simpanan","Tabungan","Rp 100.000","3 September 2020"),
            DataTransaksi("FF","Simpanan","Tabungan","Rp 100.000","4 September 2020"),
            DataTransaksi("AA","Simpanan","Tabungan","Rp 100.000","7 September 2020")
        )

        val listBulan = arrayListOf(
            DataBulan("Juni 2020",transaksiBulanJuni),
            DataBulan("Juli 2020",transaksiBulanJuli),
            DataBulan("Agustus 2020",transaksiBulanAgustus),
            DataBulan("September 2020",transaksiBulanSeptember)
        )

        val adapterBulanTransaksi = BulanAdapter(listBulan)
        listener.showData(adapterBulanTransaksi)
    }

interface Listener {
    fun showData(adapterTransaksi: BulanAdapter)
}

}