package com.example.travada.riwayat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R

class RiwayatActivity : AppCompatActivity() {
    private lateinit var presenter: RiwayatActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

    }
}