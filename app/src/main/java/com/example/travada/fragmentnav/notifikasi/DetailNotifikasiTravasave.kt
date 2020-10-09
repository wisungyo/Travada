package com.example.travada.fragmentnav.notifikasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R
import com.example.travada.fragmentnav.notifikasi.model.DataNotifikasi
import kotlinx.android.synthetic.main.activity_detail_notifikasi_travasave.*

class DetailNotifikasiTravasave : AppCompatActivity(), DetailNotifikasiTravasavePresenter.Listener {
    private lateinit var presenter: DetailNotifikasiTravasavePresenter
    private lateinit var data: DataNotifikasi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_notifikasi_travasave)

        presenter = DetailNotifikasiTravasavePresenter(this)
        intent.getParcelableExtra<DataNotifikasi>("notifikasi")?.let {
            data = it
        }

        fetchNotifikasiData()
    }

    private fun fetchNotifikasiData() {
        tvDescNotifikasi.text = data.pesan
    }
}