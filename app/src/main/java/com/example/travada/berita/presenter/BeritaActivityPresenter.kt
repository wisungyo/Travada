package com.example.travada.berita.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.berita.adapter.AdapterListBerita
import com.example.travada.sampeldata.DataBerita

class BeritaActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchData() {
        val listBerita = arrayListOf(
            DataBerita(
                R.drawable.berita_isi_saldo,
                "Isi Saldo Rekening Travada",
                "Ada Cashback Rp 6.500 buat kamu yang isi saldo rekening Travada minimum Rp 500.000 dari Bank Binar",
                "Berlaku sampai 31 Agustus 2020"),
            DataBerita(
                R.drawable.berita_paket_data,
                "Beli Paket Data",
                "Ada Cashback Rp 6.500 buat kamu yang beli paket data ke semua operator",
                "Berlaku sampai 30 Oktober 2020"),
            DataBerita(R.drawable.berita_bayar_pln,
                "Beli Token PLN",
                "Ada Cashback Rp 6.500 buat kamu yang beli token PLN melalui Travada",
                "Berlaku sampai 31 Desember  2020"),
            DataBerita(R.drawable.berita_beli_token,
                "Bayar Tagihan PLN",
                "Ada Cashback Rp 6.500 buat kamu yang bayar tagihan PLN melalui Travada",
                "Berlaku sampai 31 Desember  2020")
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
