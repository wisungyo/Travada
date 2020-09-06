package com.example.travada.mainpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.berita.BeritaActivity
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPageActivity : AppCompatActivity(),MainPageActivityPresenter.Listener {
    private lateinit var presenter: MainPageActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        presenter = MainPageActivityPresenter(this)

        presenter.fetchData()

        tv_mainpage_berita_lihat_semua.setOnClickListener {
            val intentSemuaBerita = Intent(this, BeritaActivity::class.java)
            startActivity(intentSemuaBerita)
        }
    }

    override fun showDataLayout(
        adapterTabungan: AdapterTabungan,
        adapterInformasi: AdapterInformasi,
        adapterTrip: AdapterTrip,
        adapterBerita: AdapterBerita,
        layoutTabungan: LinearLayoutManager,
        layoutInformasi: LinearLayoutManager,
        layoutTrip: LinearLayoutManager,
        layoutBerita: LinearLayoutManager
    ) {
        rv_mainpage_tabungan.adapter = adapterTabungan
        rv_mainpage_tabungan.layoutManager = layoutTabungan
        rv_mainpage_informasi.adapter = adapterInformasi
        rv_mainpage_informasi.layoutManager = layoutInformasi
        rv_mainpage_trip.adapter = adapterTrip
        rv_mainpage_trip.layoutManager = layoutTrip
        rv_mainpage_berita.adapter = adapterBerita
        rv_mainpage_berita.layoutManager = layoutBerita
    }
}