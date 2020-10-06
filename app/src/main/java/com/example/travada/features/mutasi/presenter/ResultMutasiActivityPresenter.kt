package com.example.travada.features.mutasi.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.features.mutasi.adapter.AdapterResultMutasiActivity
import com.example.travada.sampeldata.DataMutasi

class ResultMutasiActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchData() {
        val listMutasi = arrayListOf(
            DataMutasi(
                "2020-01-20",
                "Hanif Tama",
                "12345678900",
                1000000,
                "debet"
            ),
            DataMutasi(
                "2020-01-25",
                "Nanda Adi",
                "12345678900",
                1000000,
                "kredit"
            ),
            DataMutasi(
                "2020-01-30",
                "Hanif Tama",
                "12345678900",
                500000,
                "debet"
            ),
            DataMutasi(
                "2020-02-20",
                "Hanif Tama",
                "12345678900",
                1000000,
                "debet"
            ),
            DataMutasi(
                "2020-02-25",
                "Hanif Tama",
                "12345678900",
                750000,
                "kredit"
            ),
            DataMutasi(
                "2020-03-20",
                "Hanif Tama",
                "12345678900",
                1000000,
                "debet"
            )
        )

        val adapter = AdapterResultMutasiActivity(listMutasi, this)
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showData(adapter, linearLayout)
    }

    fun doConvertPDF() {
        listener.showConvertPDF()
    }

    interface Listener {
        fun showData(adapter: AdapterResultMutasiActivity, linearLayout: LinearLayoutManager)
        fun showConvertPDF()
    }
}