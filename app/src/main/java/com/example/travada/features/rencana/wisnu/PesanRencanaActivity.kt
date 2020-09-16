package com.example.travada.features.rencana.wisnu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_pesan_rencana.*

class PesanRencanaActivity : AppCompatActivity(), PesanRencanaActivityPresenter.Listener {
    private lateinit var presenter: PesanRencanaActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan_rencana)
        presenter = PesanRencanaActivityPresenter(this)

        presenter.fetchSpinnerData()
        presenter.fetchCicilanData()
    }

    override fun showCicilanData(
        adapter: AdapterPesanRencanaActivity,
        layout: LinearLayoutManager
    ) {
        rv_rencana_pesan_card.adapter = adapter
        rv_rencana_pesan_card.layoutManager = layout
    }
}