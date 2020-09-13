package com.example.travada.features.tabungan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R
import com.example.travada.features.tabungan.MainTabungan.TabunganActivity
import kotlinx.android.synthetic.main.activity_detail_form_result.*
import kotlinx.android.synthetic.main.activity_tabungan.*

class DetailFormResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_form_result)

        btnBuatSekarang.setOnClickListener {
            val backToTabungan = Intent(this, TabunganActivity::class.java)
            startActivity(backToTabungan)
        }
    }
}