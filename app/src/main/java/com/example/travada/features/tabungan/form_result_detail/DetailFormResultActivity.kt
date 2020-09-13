package com.example.travada.features.tabungan.form_result_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_detail_form_result.*

class DetailFormResultActivity : AppCompatActivity() {

    private val gabungBareng = arrayListOf(
        DataTabungBareng("Abigail Nicole", "12534530", "AA"),
        DataTabungBareng("Nofa Adelia", "4343400", "AA"),
        DataTabungBareng("Cyntia Devi", "14343400", "AA")
    )

    val adapterGabungBareng = TabungBarengAdapter(gabungBareng)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_form_result)

        // adapter
        val layoutManagerLinear = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvDetailResult.layoutManager = layoutManagerLinear
        rvDetailResult.adapter = adapterGabungBareng
        rvDetailResult.overScrollMode = View.OVER_SCROLL_NEVER
    }
}