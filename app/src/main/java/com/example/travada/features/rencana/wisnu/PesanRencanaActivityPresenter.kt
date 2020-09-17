package com.example.travada.features.rencana.wisnu

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.sampeldata.DataCicilan


class PesanRencanaActivityPresenter(val listener: Listener): AppCompatActivity() {

    fun fetchSpinnerData() {
        val arraySpinner = ArrayList<String>()
        arraySpinner.add("Item 1")
        arraySpinner.add("Item 2")
        arraySpinner.add("Item 3")

        listener.showSpinner(arraySpinner)
    }

    fun fetchCicilanData() {
        val listCicilan = arrayListOf(
            DataCicilan(
                "ABC123",
                "DP",
                6000000,
                "Januari",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 1/5",
                4500000,
                "Februari",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 2/5",
                4500000,
                "Maret",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 3/5",
                4500000,
                "April",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 4/5",
                4500000,
                "Mei",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 5/5",
                4500000,
                "Juni",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Pelunasan",
                5000000,
                "Juli",
                "Menunggu Persetujuan"
            )
        )

        val adapterRencanaPesan = AdapterPesanRencanaActivity(listCicilan, this)
        val linearLayoutRencanaPesan = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        listener.showCicilanData(adapterRencanaPesan, linearLayoutRencanaPesan)
    }

    fun addBiaya(addBiaya: Int) {
        listener.addBiaya(addBiaya)
    }

    fun addOrang(jumlahOrang: Int) {
        listener.showAddOrang(jumlahOrang + 1)
    }

    fun minOrang(jumlahOrang: Int) {
        var jumlahOrangNew = jumlahOrang - 1
        if (jumlahOrangNew < 1) jumlahOrangNew = 1
        listener.showMinOrang(jumlahOrangNew)
    }

    fun doKonfirmasi() {
        listener.showKonfirmasi()
    }


    /*
        INTERFACE
    */
    interface Listener {
        fun showSpinner(arraySpinner: ArrayList<String>)
        fun showCicilanData(adapter: AdapterPesanRencanaActivity, layout: LinearLayoutManager)
        fun addBiaya(addBiaya: Int)
        fun showAddOrang(addOrang: Int)
        fun showMinOrang(addOrang: Int)
        fun showKonfirmasi()
    }
}