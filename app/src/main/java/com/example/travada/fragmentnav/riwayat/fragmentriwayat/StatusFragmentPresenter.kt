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
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 2",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "88,000,000",
                "Disetujui"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 3",
                "1 Oktober 2022",
                "6 Oktober 2022",
                "26 Juli 2022",
                "77,000,000",
                "Ditolak"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 4",
                "1 Oktober 2021",
                "6 Oktober 2021",
                "26 Juli 2021",
                "99,000,000",
                "Expired"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 8",
                "1 Oktober 2023",
                "6 Oktober 2023",
                "26 Juli 2023",
                "44,000,000",
                "Disetujui"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 2",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "88,000,000",
                "Disetujui"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 3",
                "1 Oktober 2022",
                "6 Oktober 2022",
                "26 Juli 2022",
                "77,000,000",
                "Ditolak"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 4",
                "1 Oktober 2021",
                "6 Oktober 2021",
                "26 Juli 2021",
                "99,000,000",
                "Expired"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 8",
                "1 Oktober 2023",
                "6 Oktober 2023",
                "26 Juli 2023",
                "44,000,000",
                "Disetujui"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 2",
                "1 Oktober 2020",
                "6 Oktober 2020",
                "26 Juli 2020",
                "88,000,000",
                "Disetujui"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 3",
                "1 Oktober 2022",
                "6 Oktober 2022",
                "26 Juli 2022",
                "77,000,000",
                "Ditolak"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 4",
                "1 Oktober 2021",
                "6 Oktober 2021",
                "26 Juli 2021",
                "99,000,000",
                "Expired"
            ),
            DataRiwayat(
                R.drawable.trip,
                "Tokyo &amp; Mount Fuji 8",
                "1 Oktober 2023",
                "6 Oktober 2023",
                "26 Juli 2023",
                "44,000,000",
                "Disetujui"
            )
        )

        val adapterRiwayatStatus = AdapterRiwayatStatus(listRiwayatItem)
        val linearLayoutRiwayatStatus = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showData(adapterRiwayatStatus, linearLayoutRiwayatStatus)
    }

    interface Listener {
        fun showData(
            adapterRiwayatStatus: AdapterRiwayatStatus,
            linearLayoutRiwayatStatus: LinearLayoutManager
        )
    }
}