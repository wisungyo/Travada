package com.example.travada.berita.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.travada.R
import com.example.travada.berita.presenter.DetailBeritaActivityPresenter
import com.example.travada.sampeldata.DataBerita
import kotlinx.android.synthetic.main.activity_detail_berita.*

class DetailBeritaActivity : AppCompatActivity(), DetailBeritaActivityPresenter.Listener {
    private lateinit var presenter: DetailBeritaActivityPresenter
    private lateinit var result: DataBerita

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)
        presenter = DetailBeritaActivityPresenter(this)
        intent.getParcelableExtra<DataBerita>("DATA")?.let {
            result = it
        }

        presenter.fetchData()

        iv_detail_berita_back.setOnClickListener {
            finish()
        }

        btn_detail_berita.setOnClickListener {
            Toast.makeText(this,"Untuk saat ini promo belum bisa digunakan", Toast.LENGTH_LONG).show()
        }
    }

    override fun showData() {
        tv_detail_berita_topbar.text = result.title
        iv_detail_berita.setBackgroundResource(result.img)
        tv_detail_berita_title.text = result.title
        tv_detail_berita_subtitle.text = result.subtitle
        tv_detail_berita_date.text = result.date
    }
}