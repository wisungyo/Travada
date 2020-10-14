package com.example.travada.features.tabungan.detailtabungan

import com.example.travada.features.tabungan.adapter.BulanAdapter
import com.example.travada.features.tabungan.adapter.DetailTabunganAdapter
import com.example.travada.features.tabungan.models.DataBulan
import com.example.travada.features.tabungan.models.DataTransaksi

class TransaksiTabuganPresenter (val listener : Listener){

    fun fetchData(){
        val transaksiBulanJuni = arrayListOf(
            DataTransaksi("AA ","Simpanan","Tabungan","100.000","3 juni 2020"),
            DataTransaksi("AA ","Simpanan","Tabungan","100.000","3 juni 2020")
        )

        val transaksiBulanJuli = arrayListOf(
            DataTransaksi("AA ","Simpanan","Tabungan","100.000","3 juni 2020"),
            DataTransaksi("AA ","Simpanan","Tabungan","100.000","3 juni 2020"),
            DataTransaksi("AA ","Simpanan","Tabungan","100.000","3 juni 2020")
        )

        val transaksiBulanAgustus = arrayListOf(
            DataTransaksi("AA ","Simpanan","Tabungan","100.000","3 juni 2020"),
            DataTransaksi("AA ","Simpanan","Tabungan","100.000","3 juni 2020"),
            DataTransaksi("AA ","Simpanan","Tabungan","100.000","3 juni 2020")
        )

        val transaksiBulanSeptember = arrayListOf(
            DataTransaksi("AA ","Simpanan","Tabungan","100.000","3 juni 2020"),
            DataTransaksi("AA ","Simpanan","Tabungan","100.000","3 juni 2020"),
            DataTransaksi("AA ","Simpanan","Tabungan","100.000","3 juni 2020")
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