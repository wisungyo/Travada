package com.example.travada.fragmentnav.beranda

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.fragmentnav.beranda.adapter.AdapterBerita
import com.example.travada.fragmentnav.beranda.adapter.AdapterInformasi
import com.example.travada.fragmentnav.beranda.adapter.AdapterTabungan
import com.example.travada.fragmentnav.beranda.adapter.AdapterTrip
import com.example.travada.sampeldata.*

class BerandaFragmentPresenter(val listener: Listener): AppCompatActivity() {

    fun fetchData() {
        val dataUser = DataUser("Wisnu Agung Prasetyo", 10000000)

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
        val adapterInformasi = AdapterInformasi(listInformasi)
        val adapterTrip = AdapterTrip(listTrip)
        val adapterBerita = AdapterBerita(listBerita, this)
        val linearLayoutTabungan = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutInformasi = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutTrip = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutBerita = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        listener.showData(
            dataUser,
            adapterTabungan,
            adapterInformasi,
            adapterTrip,
            adapterBerita,
            linearLayoutTabungan,
            linearLayoutInformasi,
            linearLayoutTrip,
            linearLayoutBerita
        )
    }

    fun doMutasi() {
        listener.showMutasi()
    }

    fun doTransfer() {
        listener.showTransfer()
    }

    fun doPembelian() {
        listener.showPembelian()
    }

    fun doEwallet() {
        listener.showEwallet()
    }

    fun doTabungan() {
        listener.showTabungan()
    }

    fun doRencana() {
        listener.showRencana()
    }

    // go to detail berita
    fun goToDetailBerita(itemBerita: DataBerita) {
        listener.showDetailBerita(itemBerita)
    }

    interface Listener {
        fun showData(
            dataUser: DataUser,
            adapterTabungan: AdapterTabungan,
            adapterInformasi: AdapterInformasi,
            adapterTrip: AdapterTrip,
            adapterBerita: AdapterBerita,
            linearLayoutTabungan: LinearLayoutManager,
            linearLayoutInformasi: LinearLayoutManager,
            linearLayoutTrip: LinearLayoutManager,
            linearLayoutBerita: LinearLayoutManager
        )
        fun showMutasi()
        fun showTransfer()
        fun showPembelian()
        fun showEwallet()
        fun showTabungan()
        fun showRencana()
        fun showDetailBerita(itemBerita: DataBerita)
    }
}