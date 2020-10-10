package com.example.travada.fragmentnav.notifikasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_detail_notifikasi_travasave.*

class DetailNotifikasiTravaplan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_notifikasi_travaplan)

        btnBack.setOnClickListener {
            finish()
        }
    }
}