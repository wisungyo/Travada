package com.example.travada.features.tabungan.main_tabungan

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R

class TabunganPresenter(val listener: Listener): AppCompatActivity() {
    fun fetchCicilanData() {
        val listCicilan = arrayListOf(
            DataListWisata(
                "Pantai Ancol",
                "1.000.000",
                "1 bulan algi",
                R.drawable.leicester
            ),
            DataListWisata(
                "Pantai Ancol",
                "1.000.000",
                "1 bulan algi",
                R.drawable.leicester
            ),
            DataListWisata(
                "Pantai Ancol",
                "1.000.000",
                "1 bulan algi",
                R.drawable.leicester
            ),
            DataListWisata(
                "Pantai Ancol",
                "1.000.000",
                "1 bulan algi",
                R.drawable.leicester
            ),
            DataListWisata(
                "Pantai Ancol",
                "1.000.000",
                "1 bulan algi",
                R.drawable.leicester
            )
        )

        val adapterWisataActivity = ListWisataAdapter(listCicilan)
        val linearLayoutWisata = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showData(adapterWisataActivity, linearLayoutWisata)
    }

    interface Listener {
        fun showData(
            adapterWisataActivityActivity: ListWisataAdapter,
            linearLayoutWisataActivity: LinearLayoutManager
        )
    }
}