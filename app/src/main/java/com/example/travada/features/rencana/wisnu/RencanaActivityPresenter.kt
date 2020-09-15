package com.example.travada.features.rencana.wisnu

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.sampeldata.DataTrip

class RencanaActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchData() {
        val listTripPopuler = arrayListOf(
            DataTrip(
                R.drawable.trip,
                "Japan Castel 1",
                "Rp. 11,000,000",
                "1 hari"
            ),
            DataTrip(
                R.drawable.trip,
                "Japan Castel 2",
                "Rp. 22,000,000",
                "2 hari"
            ),
            DataTrip(
                R.drawable.trip,
                "Japan Castel 2",
                "Rp. 33,000,000",
                "3 hari"
            ),
            DataTrip(
                R.drawable.trip,
                "Japan Castel 4",
                "Rp. 44,000,000",
                "4 hari"
            ),
            DataTrip(
                R.drawable.trip,
                "Japan Castel 5",
                "Rp. 55,000,000",
                "5 hari"
            )
        )

        val listTrip = arrayListOf(
            DataTrip(
                R.drawable.trip,
                "Tokyo & Mount Fuji 1",
                "Rp. 11,000,000",
                "1 hari"
            ),
            DataTrip(
                R.drawable.trip,
                "Tokyo & Mount Fuji 2",
                "Rp. 22,000,000",
                "2 hari"
            ),
            DataTrip(
                R.drawable.trip,
                "Tokyo & Mount Fuji 3",
                "Rp. 33,000,000",
                "3 hari"
            ),
            DataTrip(
                R.drawable.trip,
                "Tokyo & Mount Fuji 4",
                "Rp. 44,000,000",
                "4 hari"
            ),
            DataTrip(
                R.drawable.trip,
                "Tokyo & Mount Fuji 5",
                "Rp. 55,000,000",
                "5 hari"
            )
        )

        val adapterTripPopuler = AdapterTripPopulerRencanaActivity(listTripPopuler)
        val adapterTrip = AdapterTripRencanaActivity(listTrip)
        val linearLayoutTripPopuler = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutTrip = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        listener.showData(
            adapterTripPopuler,
            adapterTrip,
            linearLayoutTripPopuler,
            linearLayoutTrip
        )
    }

    fun doBack() {
        listener.showBack()
    }

    interface Listener {
        fun showData(
            adapterTripPopuler: AdapterTripPopulerRencanaActivity,
            adapterTrip: AdapterTripRencanaActivity,
            linearLayoutTripPopuler: LinearLayoutManager,
            linearLayoutTrip: LinearLayoutManager
        )
        fun showBack()
    }
}