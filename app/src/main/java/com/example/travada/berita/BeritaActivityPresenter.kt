package com.example.travada.berita

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.mainpage.DataBerita

class BeritaActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchData() {
        val listBerita = arrayListOf(
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "31 Agustus 2020"),
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "31 Agustus 2020"),
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "31 Agustus 2020"),
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "31 Agustus 2020"),
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "31 Agustus 2020")
        )

        val adapterBerita = AdapterListBerita(listBerita, this)
        val layoutLinearBerita = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showDataLayout(
            adapterBerita,
            layoutLinearBerita)
    }

    fun goToDetailBerita(result: DataBerita) {
        listener.goToDetailBerita(result)
    }

    interface Listener {
        fun showDataLayout(adapterBerita: AdapterListBerita, layoutBerita: LinearLayoutManager)
        fun goToDetailBerita(result: DataBerita)
    }
}
