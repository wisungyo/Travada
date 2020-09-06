package com.example.travada.berita

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.mainpage.AdapterBerita
import com.example.travada.mainpage.DataBerita

class BeritaActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchData() {
        val listBerita = arrayListOf(
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "31 Agustus 2020"),
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "31 Agustus 2020"),
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "31 Agustus 2020"),
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "31 Agustus 2020"),
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "Berlaku sampai 31 Agustus 2020")
        )

        val adapterBerita = AdapterListBerita(listBerita)
        val layoutLinearBerita = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showDataLayout(
            adapterBerita,
            layoutLinearBerita)
    }

    interface Listener {
        fun showDataLayout(adapterBerita: AdapterListBerita, layoutBerita: LinearLayoutManager)
    }
}
