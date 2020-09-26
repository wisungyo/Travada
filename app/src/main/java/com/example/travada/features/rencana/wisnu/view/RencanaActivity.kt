package com.example.travada.features.rencana.wisnu.view

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.rencana.wisnu.adapter.AdapterTripPopulerRencanaActivity
import com.example.travada.features.rencana.wisnu.adapter.AdapterTripRencanaActivity
import com.example.travada.features.rencana.wisnu.presenter.RencanaActivityPresenter
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_rencana.*

class RencanaActivity : AppCompatActivity(), RencanaActivityPresenter.Listener {
    private lateinit var presenter: RencanaActivityPresenter
    private lateinit var progressDialog: ProgressDialog
//    val MyFragment = LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rencana)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Mohon tunggu...")
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

    override fun showTripPilihan(
        adapterTrip: AdapterTripRencanaActivity,
        linearLayoutTrip: LinearLayoutManager
    ) {
        rv_rencana_trip.adapter = adapterTrip
        rv_rencana_trip.layoutManager = linearLayoutTrip
    }

    override fun showTripPopuler(
        adapterTripPopuler: AdapterTripPopulerRencanaActivity,
        linearLayoutTripPopuler: LinearLayoutManager
    ) {
        rv_rencana_trip_populer.adapter = adapterTripPopuler
        rv_rencana_trip_populer.layoutManager = linearLayoutTripPopuler
    }

    override fun showBack() {
        finish()
    }

    override fun showItemClicked(position: Int) {
        val intentItemClicked = Intent(this, PesanRencanaActivity::class.java)
        intentItemClicked.putExtra("DESTINASI_ID", position)
        startActivity(intentItemClicked)
    }

    override fun showDataError(localizedMessage: String?) {
        Toast.makeText(
            this,
            "Error : ${localizedMessage}",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showProgressDialog() {
        progressDialog.show()
//        val fm=supportFragmentManager
//        MyFragment.isCancelable = false
//        MyFragment.show(fm, "Fragment")
    }

    override fun dismissProgressDialog() {
        progressDialog.dismiss()
//        MyFragment.dismiss()
    }
}