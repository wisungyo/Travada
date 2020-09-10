package com.example.travada.fragmentnav.riwayat.fragmentriwayat

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.fragmentnav.riwayat.adapter.AdapterRiwayatStatus
import com.example.travada.sampeldata.DataRiwayat

class StatusFragmentPresenter (val listener: Listener): AppCompatActivity() {

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
                "Disetujui"
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
                "Ditolak"
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
                "Expired"
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
                "Ditolak"
            )
        )

        val adapterRiwayatStatus = AdapterRiwayatStatus(listRiwayatItem, this)
        val linearLayoutRiwayatStatus = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showData(adapterRiwayatStatus, linearLayoutRiwayatStatus)
    }
    
    fun goToDetailRiwayat(dataRiwayat: DataRiwayat) {
        listener.showDetailRiwayat(dataRiwayat)
    }

    interface Listener {
        fun showData(
            adapterRiwayatStatus: AdapterRiwayatStatus,
            linearLayoutRiwayatStatus: LinearLayoutManager
        )
        fun showDetailRiwayat(dataRiwayat: DataRiwayat)
    }
}