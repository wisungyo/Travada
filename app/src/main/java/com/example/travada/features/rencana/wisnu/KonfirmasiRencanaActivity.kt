package com.example.travada.features.rencana.wisnu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_konfirmasi_rencana.*
import kotlinx.android.synthetic.main.activity_pesan_rencana.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class KonfirmasiRencanaActivity : AppCompatActivity(), KonfirmasiRencanaActivityPresenter.Listener {
    private lateinit var presenter: KonfirmasiRencanaActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_rencana)
        presenter = KonfirmasiRencanaActivityPresenter(this)

        val bundleKonfirmasi = intent.extras
        val jumlahOrang = bundleKonfirmasi?.getInt("JUMLAH_ORANG")
        val jumlahBiaya = bundleKonfirmasi?.getInt("JUMLAH_BIAYA")

        if (jumlahOrang != null && jumlahBiaya != null) {
            presenter.fetchDataCicilan(jumlahOrang, jumlahBiaya)
        }
        presenter.fetchDataCicilanLayout()
    }

    override fun showDataCicilan(jumlahOrang: Int, jumlahBiaya: Int) {
        tv_konfirmasi_rencana_jumlah_orang.text = "$jumlahOrang orang"

        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        tv_konfirmasi_rencana_jumlah_biaya.text = "Rp. ${df.format(jumlahBiaya)}"
    }

    override fun showCicilanList(
        adapterKonfirmasiRencanaActivity: AdapterKonfirmasiRencanaActivity,
        linearLayoutManager: LinearLayoutManager
    ) {
        rv_konfirmasi_rencana.adapter = adapterKonfirmasiRencanaActivity
        rv_konfirmasi_rencana.layoutManager = linearLayoutManager
    }

}