package com.example.travada.detailriwayat

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.sampeldata.DataCicilan
import com.example.travada.sampeldata.DataRiwayat

class DetailRiwayatActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchCicilanData() {
        val listCicilan = arrayListOf(
            DataCicilan(
                "ABC123",
                "DP",
                2500000,
                "09 Juli 2020",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 1/4",
                2500000,
                "09 Agustus 2020",
                "Dibayar"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 2/4",
                2500000,
                "09 September 2020",
                "Menunggu Pembayaran"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 3/4",
                2500000,
                "09 Oktober 2020",
                "Expired"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 4/4",
                2500000,
                "09 Agustus 2020",
                "November Pembayaran"
            )
        )

        val adapterDetailRiwayat = AdapterDetailRiwayatActivity(listCicilan, this)
        val linearLayoutDetailRiwayat = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showData(adapterDetailRiwayat, linearLayoutDetailRiwayat)
    }

    interface Listener {
        fun showData(
            adapterDetailRiwayatActivity: AdapterDetailRiwayatActivity,
            linearLayoutDetailRiwayatActivity: LinearLayoutManager)
    }
}