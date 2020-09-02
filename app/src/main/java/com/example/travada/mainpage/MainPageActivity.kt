package com.example.travada.mainpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val listTabungan = arrayListOf(
            DataTabungan(R.drawable.gradation, "Pulau Komodo 2020"),
            DataTabungan(R.drawable.gradation, "Gunung Bromo 2020"),
            DataTabungan(R.drawable.gradation, "Ranu Kumbolo 2020"),
            DataTabungan(R.drawable.gradation, "Pulau Bali 2020"),
            DataTabungan(R.drawable.gradation, "Pulau Lombok 2020")
        )

        val listInformasi = arrayListOf(
            DataInformasi(R.drawable.gradation),
            DataInformasi(R.drawable.gradation),
            DataInformasi(R.drawable.gradation),
            DataInformasi(R.drawable.gradation),
            DataInformasi(R.drawable.gradation)
        )

        val adapterTabungan = AdapterTabungan(listTabungan)
        rv_mainpage_tabungan.adapter = adapterTabungan
        val layoutLinearTabungan = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_mainpage_tabungan.layoutManager = layoutLinearTabungan

        val adapterInformasi = AdapterInformasi(listInformasi)
        rv_mainpage_informasi.adapter = adapterInformasi
        val layoutLinearInformasi = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_mainpage_informasi.layoutManager = layoutLinearInformasi
    }
}