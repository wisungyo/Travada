package com.example.travada.berita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R
import com.example.travada.sampeldata.DataBerita
import kotlinx.android.synthetic.main.activity_detail_berita.*

class DetailBeritaActivity : AppCompatActivity() {
    private lateinit var presenter: DetailBeritaActivityPresenter
    private lateinit var result: DataBerita

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)
        presenter = DetailBeritaActivityPresenter()
        intent.getParcelableExtra<DataBerita>("DATA")?.let {
            result = it
        }

        iv_detail_berita.setBackgroundResource(result.img)
        tv_detail_berita_title.text = result.title
        tv_detail_berita_subtitle.text = result.subtitle
        tv_detail_berita_date.text = result.date

        iv_detail_berita_back.setOnClickListener {
            finish()
        }
    }
}