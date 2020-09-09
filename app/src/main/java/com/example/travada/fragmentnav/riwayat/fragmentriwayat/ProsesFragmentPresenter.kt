package com.example.travada.fragmentnav.riwayat.fragmentriwayat

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.fragmentnav.riwayat.adapter.AdapterRiwayatProses
import com.example.travada.sampeldata.DataRiwayat

class ProsesFragmentPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchDataRiwayat() {
        val listRiwayatItem = arrayListOf(
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 1",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "33,000,000",
                "Menunggu"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 5",
                "2 Oktober 2022",
                "2 Oktober 2022",
                "22 Juli 2022",
                "55,000,000",
                "Menunggu"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 1",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "33,000,000",
                "Menunggu"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 5",
                "2 Oktober 2022",
                "2 Oktober 2022",
                "22 Juli 2022",
                "55,000,000",
                "Menunggu"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 1",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "33,000,000",
                "Menunggu"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 5",
                "2 Oktober 2022",
                "2 Oktober 2022",
                "22 Juli 2022",
                "55,000,000",
                "Menunggu"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 1",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "33,000,000",
                "Menunggu"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 5",
                "2 Oktober 2022",
                "2 Oktober 2022",
                "22 Juli 2022",
                "55,000,000",
                "Menunggu"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 1",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "33,000,000",
                "Menunggu"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 5",
                "2 Oktober 2022",
                "2 Oktober 2022",
                "22 Juli 2022",
                "55,000,000",
                "Menunggu"
            )
        )

        val adapterRiwayatProses = AdapterRiwayatProses(listRiwayatItem)
        val linearLayoutRiwayatProses = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showData(adapterRiwayatProses, linearLayoutRiwayatProses)
    }

    interface Listener {
        fun showData(
            adapterRiwayatProses: AdapterRiwayatProses,
            linearLayoutRiwayatProses: LinearLayoutManager
        )
    }
}