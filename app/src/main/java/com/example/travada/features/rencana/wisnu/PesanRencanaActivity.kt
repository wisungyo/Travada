package com.example.travada.features.rencana.wisnu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_pesan_rencana.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.properties.Delegates

class PesanRencanaActivity : AppCompatActivity(), PesanRencanaActivityPresenter.Listener {
    private lateinit var presenter: PesanRencanaActivityPresenter
    var jumlahBiaya by Delegates.notNull<Int>()
    var jumlahOrang by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan_rencana)
        presenter = PesanRencanaActivityPresenter(this)
        jumlahBiaya = 0
        jumlahOrang = 1

        presenter.fetchSpinnerData()
        presenter.fetchCicilanData()

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
            presenter.doKonfirmasi()
        }
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

    override fun showBack() {
        finish()
    }

    override fun addBiaya(addBiaya: Int) {
        jumlahBiaya+=addBiaya

        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        tv_rencana_pesan_total.text = "Rp. ${df.format(jumlahBiaya)}"
    }

    override fun showAddOrang(addOrang: Int) {
        jumlahOrang = addOrang
        et_rencana_pesan_jumlah_orang.setText(jumlahOrang.toString())
    }

    override fun showMinOrang(addOrang: Int) {
        jumlahOrang = addOrang
        et_rencana_pesan_jumlah_orang.setText(jumlahOrang.toString())
    }

    override fun showKonfirmasi() {
        val intentKonfirmasi = Intent(this, KonfirmasiRencanaActivity::class.java)
        val bundleKonfirmasi = Bundle()
        bundleKonfirmasi.putInt("JUMLAH_ORANG", jumlahOrang)
        bundleKonfirmasi.putInt("JUMLAH_BIAYA", jumlahBiaya)
        intentKonfirmasi.putExtras(bundleKonfirmasi)
        startActivity(intentKonfirmasi)
    }
}