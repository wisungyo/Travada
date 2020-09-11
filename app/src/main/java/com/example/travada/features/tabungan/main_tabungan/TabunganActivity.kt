package com.example.travada.features.tabungan.main_tabungan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R
import com.example.travada.features.tabungan.form_one.FormTabunganActivityOne
import kotlinx.android.synthetic.main.activity_tabungan.*

class TabunganActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabungan)

        btnBuatLiburan.setOnClickListener {
            val goToFormTabunganOne = Intent(this,
                FormTabunganActivityOne::class.java)
            startActivity(goToFormTabunganOne)
//            /
        }
    }
}