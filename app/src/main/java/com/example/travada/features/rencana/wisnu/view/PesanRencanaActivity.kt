package com.example.travada.features.rencana.wisnu.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.wisnu.adapter.AdapterPesanRencanaActivity
import com.example.travada.features.rencana.wisnu.presenter.PesanRencanaActivityPresenter
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_pesan_rencana.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.properties.Delegates

class PesanRencanaActivity : AppCompatActivity(), PesanRencanaActivityPresenter.Listener {
    private lateinit var presenter: PesanRencanaActivityPresenter
    var jumlahBiaya by Delegates.notNull<Int>()
    var jumlahOrang by Delegates.notNull<Int>()
    val MyFragment= LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan_rencana)

        presenter = PesanRencanaActivityPresenter(this)
        val intentId = intent.getIntExtra("DESTINASI_ID", 3)
        jumlahBiaya = 0
        jumlahOrang = 1

        presenter.fetchMainData(intentId)
        presenter.fetchCicilanData(intentId, jumlahOrang)

        spinner_rencana_pesan.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                /*
                    DO SOMETHING HERE
                 */
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                /*
                    DO SOMETHING HERE
                */
            }
        }

        iv_rencana_pesan_back.setOnClickListener {
            presenter.doBack()
        }

        iv_rencana_pesan_add.setOnClickListener {
            presenter.addOrang(jumlahOrang)
        }

        iv_rencana_pesan_min.setOnClickListener {
            presenter.minOrang(jumlahOrang)
        }

        btn_rencana_pesan.setOnClickListener {
            presenter.doKonfirmasi(intentId)
        }
    }

    override fun showMainData(data: GetDestinasiResponse.Data) {
        tv_rencana_pesan_topbar.text = data.namaTrip

        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        tv_rencana_pesan_total.text = "Rp. ${df.format(data.hargaSatuan)}"
    }

    override fun showSpinner(arraySpinner: ArrayList<String>) {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            arraySpinner
        )

        adapter.setDropDownViewResource(R.layout.rencana_pesan_spinner_item)

        spinner_rencana_pesan.adapter = adapter
    }

    override fun showCicilanData(
        adapter: AdapterPesanRencanaActivity,
        layout: LinearLayoutManager
    ) {
        rv_rencana_pesan_card.adapter = adapter
        rv_rencana_pesan_card.layoutManager = layout
    }

    override fun showDataError(localizedMessage: String?) {
        Toast.makeText(
            this,
            "Error : $localizedMessage",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showBack() {
        finish()
    }

    override fun showAddOrang(addOrang: Int) {
        jumlahOrang = addOrang
        et_rencana_pesan_jumlah_orang.setText(jumlahOrang.toString())

        // refresh table view
        presenter = PesanRencanaActivityPresenter(this)
        val intentId = intent.getIntExtra("DESTINASI_ID", 3)
        presenter.fetchCicilanData(intentId, jumlahOrang)
    }

    override fun showMinOrang(addOrang: Int) {
        jumlahOrang = addOrang
        et_rencana_pesan_jumlah_orang.setText(jumlahOrang.toString())

        // refresh table view
        presenter = PesanRencanaActivityPresenter(this)
        val intentId = intent.getIntExtra("DESTINASI_ID", 3)
        presenter.fetchCicilanData(intentId, jumlahOrang)
    }

    override fun showKonfirmasi(intentPosition: Int) {
        val intentKonfirmasi = Intent(this, KonfirmasiRencanaActivity::class.java)
        intentKonfirmasi.putExtra("DESTINASI_ID", intentPosition)
        intentKonfirmasi.putExtra("JUMLAH_ORANG", jumlahOrang)
        intentKonfirmasi.putExtra("JUMLAH_BIAYA", this.jumlahBiaya)
        startActivity(intentKonfirmasi)
    }

    override fun showJumlahBiaya(jumlahBiaya: Int) {
        this.jumlahBiaya = jumlahBiaya
        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        tv_rencana_pesan_total.text = "Rp. ${df.format(this.jumlahBiaya)}"
    }

    override fun showLoadingDialog() {
        val fm=supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }
}
