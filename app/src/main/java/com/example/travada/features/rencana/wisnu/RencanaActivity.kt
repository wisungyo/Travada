package com.example.travada.features.rencana.wisnu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_rencana.*

class RencanaActivity : AppCompatActivity(), RencanaActivityPresenter.Listener {
    private lateinit var presenter: RencanaActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rencana)
        presenter = RencanaActivityPresenter(this)
        presenter.fetchData()

        iv_rencana_back.setOnClickListener {
            presenter.doBack()
        }

        tv_rencana_trip_lihat_semua.setOnClickListener {
            //
            // INTENT TO ALL TRIP LIST (MAS NANDA)
            //
        }
    }

    override fun showData(
        adapterTripPopuler: AdapterTripPopulerRencanaActivity,
        adapterTrip: AdapterTripRencanaActivity,
        linearLayoutTripPopuler: LinearLayoutManager,
        linearLayoutTrip: LinearLayoutManager
    ) {
        rv_rencana_trip_populer.adapter = adapterTripPopuler
        rv_rencana_trip_populer.layoutManager = linearLayoutTripPopuler
        rv_rencana_trip.adapter = adapterTrip
        rv_rencana_trip.layoutManager = linearLayoutTrip
    }

    override fun showBack() {
        finish()
    }
}