package com.example.travada.berita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.mainpage.AdapterBerita
import kotlinx.android.synthetic.main.activity_berita.*

class BeritaActivity : AppCompatActivity(), BeritaActivityPresenter.Listener {
    private lateinit var presenter: BeritaActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_berita)
        presenter = BeritaActivityPresenter(this)

        presenter.fetchData()

        iv_berita_back.setOnClickListener {
            finish()
        }
    }

    override fun showDataLayout(adapterBerita: AdapterListBerita, layoutBerita: LinearLayoutManager) {
        rv_berita.adapter = adapterBerita
        rv_berita.layoutManager = layoutBerita
    }
}