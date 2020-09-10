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
                "ABC123",
                R.drawable.trip,
                "Tokyo & Mount Fuji 1",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "11,000,000",
                "5,000,000",
                "1,000,000",
                3,
                "Menunggu"
            ),
            DataRiwayat(
                "DEF456",
                R.drawable.trip,
                "Tokyo & Mount Fuji 2",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "22,000,000",
                "7,000,000",
                "1,500,000",
                4,
                "Menunggu"
            ),
            DataRiwayat(
                "ABC123",
                R.drawable.trip,
                "Tokyo & Mount Fuji 3",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "33,000,000",
                "5,000,000",
                "1,000,000",
                3,
                "Menunggu"
            ),
            DataRiwayat(
                "DEF456",
                R.drawable.trip,
                "Tokyo & Mount Fuji 4",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "44,000,000",
                "7,000,000",
                "1,500,000",
                4,
                "Menunggu"
            ),
            DataRiwayat(
                "ABC123",
                R.drawable.trip,
                "Tokyo & Mount Fuji 5",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "55,000,000",
                "5,000,000",
                "1,000,000",
                3,
                "Menunggu"
            ),
            DataRiwayat(
                "DEF456",
                R.drawable.trip,
                "Tokyo & Mount Fuji 6",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "66,000,000",
                "7,000,000",
                "1,500,000",
                4,
                "Menunggu"
            ),
            DataRiwayat(
                "ABC123",
                R.drawable.trip,
                "Tokyo & Mount Fuji 7",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "77,000,000",
                "5,000,000",
                "1,000,000",
                3,
                "Menunggu"
            ),
            DataRiwayat(
                "DEF456",
                R.drawable.trip,
                "Tokyo & Mount Fuji 8",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "88,000,000",
                "7,000,000",
                "1,500,000",
                4,
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