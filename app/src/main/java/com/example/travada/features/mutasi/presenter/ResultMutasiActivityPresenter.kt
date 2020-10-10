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
                "Transfer dari ENNO DESTIAS 12345678900",
                "Transfer",
                1000000,
                "debet"
            ),
            DataMutasi(
                "2020-01-21",
                "Topup ke Pulau Komodo 2020",
                "Travasave",
                200000,
                "kredit"
            ),
            DataMutasi(
                "2020-01-23",
                "Pembayaran DP #12345678900",
                "Travaplan",
                1000000,
                "kredit"
            ),
            DataMutasi(
                "2020-01-28",
                "Transfer ke NOFA DWI ADELIA 12345678900",
                "Transfer",
                1500000,
                "kredit"
            ),
            DataMutasi(
                "2020-01-30",
                "Pencarian Dana Labuan Bajo 2021",
                "Travasave",
                2000000,
                "debet"
            ),
            DataMutasi(
                "2020-02-20",
                "Transfer dari ENNO DESTIAS 12345678900",
                "Transfer",
                1000000,
                "debet"
            ),
            DataMutasi(
                "2020-02-21",
                "Topup ke Pulau Komodo 2020",
                "Travasave",
                200000,
                "kredit"
            ),
            DataMutasi(
                "2020-03-23",
                "Pembayaran DP #12345678900",
                "Travaplan",
                1000000,
                "kredit"
            ),
            DataMutasi(
                "2020-03-28",
                "Transfer ke NOFA DWI ADELIA 12345678900",
                "Transfer",
                1500000,
                "kredit"
            ),
            DataMutasi(
                "2020-03-30",
                "Pencarian Dana Labuan Bajo 2021",
                "Travasave",
                2000000,
                "debet"
            ),
            DataMutasi(
                "2020-04-20",
                "Transfer dari ENNO DESTIAS 12345678900",
                "Transfer",
                1000000,
                "debet"
            ),
            DataMutasi(
                "2020-04-21",
                "Topup ke Pulau Komodo 2020",
                "Travasave",
                200000,
                "kredit"
            ),
            DataMutasi(
                "2020-04-23",
                "Pembayaran DP #12345678900",
                "Travaplan",
                1000000,
                "kredit"
            ),
            DataMutasi(
                "2020-04-28",
                "Transfer ke NOFA DWI ADELIA 12345678900",
                "Transfer",
                1500000,
                "kredit"
            ),
            DataMutasi(
                "2020-04-30",
                "Pencarian Dana Labuan Bajo 2021",
                "Travasave",
                2000000,
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