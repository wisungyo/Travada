package com.example.travada.detailriwayat.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.detailriwayat.adapter.AdapterSpinnerBayarCicilan
import com.example.travada.sampeldata.DataSpinnerCicilan

class BayarCicilanActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchData() {
        val listSpinner = arrayListOf(
            DataSpinnerCicilan("Saldo", "Saldo", 50000000),
            DataSpinnerCicilan("Trava Save", "Labuan Bajo 2020", 5000000),
            DataSpinnerCicilan("Trava Save", "Tokyo 2020", 10000000)
        )

//        listener.showSpinner(listSpinner)
        val adapterSpinner  = AdapterSpinnerBayarCicilan(listSpinner, this)
        val linearLayout    = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        listener.showSpinner(adapterSpinner, linearLayout)
    }



    fun doKonfirmasi() {
        listener.showDialogKonfirmasi(
            "Konfirmasi Pembayaran",
            "Tagihan akan diambil dari saldo kamu"
        )
    }

    interface Listener {
        fun showSpinner(
            adapterSpinner: AdapterSpinnerBayarCicilan,
            linearLayout: LinearLayoutManager
        )
        fun showDialogKonfirmasi(title: String, subtitle: String)
    }
}