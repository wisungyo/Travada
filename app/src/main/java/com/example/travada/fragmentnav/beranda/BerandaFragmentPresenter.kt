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
            DataTabungan(R.drawable.tabungan, "Pulau Komodo", 80),
            DataTabungan(R.drawable.tabungan, "Gunung Bromo", 60),
            DataTabungan(R.drawable.tabungan, "Ranu Kumbolo", 30),
            DataTabungan(R.drawable.tabungan, "Pulau Bali",50),
            DataTabungan(R.drawable.tabungan, "Kota Blitar",10)
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

        val listBerita = arrayListOf(
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada 1",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "Berlaku sampai 31 Agustus 2021"),
            DataBerita(
                R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada 2",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "Berlaku sampai 31 Agustus 2022"),
            DataBerita(R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada 3",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "Berlaku sampai 31 Agustus 2023"),
            DataBerita(R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada 4",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "Berlaku sampai 31 Agustus 2024"),
            DataBerita(R.drawable.berita,
                "Cashback Isi Saldo Rekening Travada 5",
                "Ada Cashback Rp. 6.500 buat kamu yg isi saldo rekening Travada minimum Rp. 500.000 dari Bank Binar.",
                "Berlaku sampai 31 Agustus 2025")
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

    fun doLihatSemuaBerita() {
        listener.showLihatSemuaBerita()
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
        fun showLihatSemuaBerita()
    }
}