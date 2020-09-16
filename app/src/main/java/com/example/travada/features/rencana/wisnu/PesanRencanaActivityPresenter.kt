package com.example.travada.features.rencana.wisnu

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.sampeldata.DataCicilan

class PesanRencanaActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchSpinnerData() {

    }

    fun fetchCicilanData() {
        val listCicilan = arrayListOf(
            DataCicilan(
                "ABC123",
                "DP",
                "4,500,000",
                "Januari",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 1/5",
                "4,500,000",
                "Februari",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 2/5",
                "4,500,000",
                "Maret",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 3/5",
                "4,500,000",
                "April",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 4/5",
                "4,500,000",
                "Mei",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 5/5",
                "4,500,000",
                "Juni",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Pelunasan",
                "4,500,000",
                "Juli",
                "Menunggu Persetujuan"
            )
        )

        val adapterRencanaPesan = AdapterPesanRencanaActivity(listCicilan)
        val linearLayoutRencanaPesan = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showCicilanData(adapterRencanaPesan, linearLayoutRencanaPesan)
    }

    interface Listener {
        fun showCicilanData(adapter: AdapterPesanRencanaActivity, layout: LinearLayoutManager)
    }
}