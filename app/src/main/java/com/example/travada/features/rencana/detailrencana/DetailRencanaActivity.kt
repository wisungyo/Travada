package com.example.travada.features.rencana.detailrencana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_detail_rencana.*
import kotlinx.android.synthetic.main.activity_tabungan.*

class DetailRencanaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_rencana)
        nestedView.overScrollMode = View.OVER_SCROLL_NEVER
    }
}