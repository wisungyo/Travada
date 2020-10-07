package com.example.travada.features.tabungan.maintabungan

import com.example.travada.R
import com.example.travada.features.tabungan.adapter.ListWisataAdapter
import com.example.travada.features.tabungan.models.DataWisata

class TabunganPresenter(val listener: Listener) {
    fun fetchWisataPilihanData() {
        val listWisataPilihan = arrayListOf(
            DataWisata(
                "Pantai Ancol",
                "1.000.000",
                "5",
                R.drawable.leicester
            ),
            DataWisata(
                "Pantai Ancol",
                "1.000.000",
                "6",
                R.drawable.leicester
            ),
            DataWisata(
                "Pantai Ancol",
                "1.000.000",
                "4",
                R.drawable.leicester
            )
        )
        val adapterWisataPilihan = ListWisataAdapter(listWisataPilihan)
        listener.showData(adapterWisataPilihan)
    }

    fun goToDetailTabunganWisata(dataWisata: DataWisata){
        listener.showDetailTabunganWisata(dataWisata)
    }

    interface Listener {
        fun showData( adapterWisataPilihan: ListWisataAdapter)
        fun showDetailTabunganWisata(dataWisata : DataWisata)
    }
}