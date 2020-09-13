package com.example.travada.features.tabungan.detailtabungan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.PageAdapter
import kotlinx.android.synthetic.main.activity_detail_tabungan.*

class DetailTabunganActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tabungan)

        viewPagerMain.adapter =
            PageAdapter(
                supportFragmentManager
            )
        tabMain.setupWithViewPager(viewPagerMain)
    }
}