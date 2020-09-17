package com.example.travada.features.rencana.wisnu

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_konfirmasi_rencana.*
import kotlinx.android.synthetic.main.activity_pesan_rencana.*
import kotlinx.android.synthetic.main.activity_top_up.*
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
            presenter.checkNextButtonCondition(jumlahOrang)
        }
        presenter.fetchDataCicilanLayout()

        iv_konfirmasi_rencana_back.setOnClickListener {
            presenter.doBack()
        }

        btn_konfirmas_rencana.setOnClickListener {
            presenter.nextButtonClicked()
        }
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

    override fun showNextButtonCondition(condition: Boolean) {
        if (!condition) {
            btn_konfirmas_rencana.isEnabled = false
            btn_konfirmas_rencana.isClickable = false
            btn_konfirmas_rencana.setBackgroundResource(R.drawable.bg_konfirmasi_rencana_btn_disable)
            btn_konfirmas_rencana.setTextColor(Color.parseColor("#777777"))
        } else {
            btn_konfirmas_rencana.isEnabled = true
            btn_konfirmas_rencana.isClickable = true
            btn_konfirmas_rencana.setBackgroundResource(R.drawable.bg_konfirmasi_rencana_btn)
            btn_konfirmas_rencana.setTextColor(Color.parseColor("#ffffff"))
        }
    }

    override fun showNextButtonClicked(title: String) {
        DialogKonfirmasi.newInstance(title).show(supportFragmentManager, DialogKonfirmasi.TAG)
    }

    override fun showBack() {
        finish()
    }

}