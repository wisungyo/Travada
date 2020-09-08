package com.example.travada.mainpage

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R

class MainPageActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchData() {
        val listTabungan = arrayListOf(
            DataTabungan(R.drawable.tabungan, "Pulau Komodo 2020", 80),
            DataTabungan(R.drawable.tabungan, "Gunung Bromo 2020", 60),
            DataTabungan(R.drawable.tabungan, "Ranu Kumbolo 2020", 30),
            DataTabungan(R.drawable.tabungan, "Pulau Bali 2020",50),
            DataTabungan(R.drawable.tabungan, "Pulau Lombok 2020",10)
        )

        val listInformasi = arrayListOf(
            DataInformasi(R.drawable.informasi),
            DataInformasi(R.drawable.informasi),
            DataInformasi(R.drawable.informasi),
            DataInformasi(R.drawable.informasi),
            DataInformasi(R.drawable.informasi)
        )

        val listTrip = arrayListOf(
            DataTrip(
                R.drawable.trip,
                "Tokyo & Mount Fuji",
                "Rp. 16.000.000",
                "5 hari"
            ),
            DataTrip(
                R.drawable.trip,
                "Tokyo & Mount Fuji",
                "Rp. 16.000.000",
                "5 hari"
            ),
            DataTrip(
                R.drawable.trip,
                "Tokyo & Mount Fuji",
                "Rp. 16.000.000",
                "5 hari"
            ),
            DataTrip(
                R.drawable.trip,
                "Tokyo & Mount Fuji",
                "Rp. 16.000.000",
                "5 hari"
            ),
            DataTrip(
                R.drawable.trip,
                "Tokyo & Mount Fuji",
                "Rp. 16.000.000",
                "5 hari"
            )
        )

        val listBerita = arrayListOf(
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "Berlaku sampai 31 Agustus 2020"),
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "Berlaku sampai 31 Agustus 2020"),
            DataBerita(R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "Berlaku sampai 31 Agustus 2020"),
            DataBerita(R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "Berlaku sampai 31 Agustus 2020"),
            DataBerita(R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "Berlaku sampai 31 Agustus 2020")
        )

        val adapterTabungan = AdapterTabungan(listTabungan)
        val layoutLinearTabungan = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adapterInformasi = AdapterInformasi(listInformasi)
        val layoutLinearInformasi = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adapterTrip = AdapterTrip(listTrip)
        val layoutLinearTrip = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adapterBerita = AdapterBerita(listBerita)
        val layoutLinearBerita = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        listener.showDataLayout(
            adapterTabungan,
            adapterInformasi,
            adapterTrip,
            adapterBerita,
            layoutLinearTabungan,
            layoutLinearInformasi,
            layoutLinearTrip,
            layoutLinearBerita
        )
    }

    interface Listener {
        fun showDataLayout(
            adapterTabungan: AdapterTabungan,
            adapterInformasi: AdapterInformasi,
            adapterTrip: AdapterTrip,
            adapterBerita: AdapterBerita,
            layoutTabungan: LinearLayoutManager,
            layoutInformasi: LinearLayoutManager,
            layoutTrip: LinearLayoutManager,
            layoutBerita: LinearLayoutManager
        )
    }
}