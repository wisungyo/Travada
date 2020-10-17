package com.example.travada.features.mutasi.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.travada.R
import com.example.travada.features.mutasi.presenter.MutasiActivityPresenter
import com.example.travada.features.rencana.pojo.GetNasabah
import kotlinx.android.synthetic.main.activity_mutasi.*
import java.util.*

class MutasiActivity : AppCompatActivity(), MutasiActivityPresenter.Listener {
    lateinit var presenter: MutasiActivityPresenter
    var date        = false
    var mingguIni   = false
    var bulanIni    = false
    var tahunIni    = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutasi)
        presenter = MutasiActivityPresenter(this)

        presenter.fetchData()

        iv_mutasi_back.setOnClickListener {
            finish()
        }

        tv_mutasi_start.setOnClickListener {
            resetNextButton()
            resetBackgroundButtons()
            resetButtonCondition()
            presenter.getTheDate(this, "start", tv_mutasi_start.text.toString())
        }

        tv_mutasi_end.setOnClickListener {
            resetNextButton()
            resetBackgroundButtons()
            resetButtonCondition()
            presenter.getTheDate(this, "end", tv_mutasi_end.text.toString())
        }

        tv_mutasi_minggu_ini.setOnClickListener {
            resetNextButton()
            resetBackgroundButtons()
            resetDateCondition()  
            resetButtonCondition()
            tv_mutasi_minggu_ini.setBackgroundColor(Color.parseColor("#EAF7FF"))
            mingguIni = true
            checkButtonNextCondition()
        }

        tv_mutasi_bulan_ini.setOnClickListener {
            resetNextButton()
            resetBackgroundButtons()
            resetDateCondition()
            resetButtonCondition()
            tv_mutasi_bulan_ini.setBackgroundColor(Color.parseColor("#EAF7FF"))
            bulanIni = true
            checkButtonNextCondition()
        }

        tv_mutasi_tahun_ini.setOnClickListener {
            resetNextButton()
            resetBackgroundButtons()
            resetDateCondition()
            resetButtonCondition()
            tv_mutasi_tahun_ini.setBackgroundColor(Color.parseColor("#EAF7FF"))
            tahunIni = true
            checkButtonNextCondition()
        }

        btn_mutasi.setOnClickListener {
            presenter.goToResultMutasi()
        }
    }

    private fun resetButtonCondition() {
        date        = false
        mingguIni   = false
        bulanIni    = false
        tahunIni    = false
    }

    private fun resetDateCondition() {
        tv_mutasi_start.text = "Dari Tanggal"
        tv_mutasi_end.text   = "Sampai Tanggal"
    }

    private fun resetBackgroundButtons() {
        tv_mutasi_minggu_ini.setBackgroundColor(Color.parseColor("#ffffff"))
        tv_mutasi_bulan_ini.setBackgroundColor(Color.parseColor("#ffffff"))
        tv_mutasi_tahun_ini.setBackgroundColor(Color.parseColor("#ffffff"))
    }

    private fun resetNextButton() {
        btn_mutasi.isEnabled = false
        btn_mutasi.setBackgroundResource(R.drawable.bg_konfirmasi_rencana_btn_disable)
    }

    private fun checkButtonNextCondition() {
        checkDateValidation()

        if (date || mingguIni || bulanIni || tahunIni) {
            btn_mutasi.setBackgroundResource(R.drawable.bg_konfirmasi_rencana_btn)
            btn_mutasi.isEnabled = true
        } else {
            btn_mutasi.setBackgroundResource(R.drawable.bg_konfirmasi_rencana_btn_disable)
            btn_mutasi.isEnabled = false
        }
    }

    private fun checkDateValidation() = when {
        tv_mutasi_start.text.toString() == "Dari Tanggal" || tv_mutasi_end.text.toString() == "Sampai Tanggal" -> {
            date = false
        }
        else -> {
            date = true
        }
    }

    override fun showResultMutasi() {
        when {
            mingguIni -> {
                Toast.makeText(this, "Minggu Ini Activated", Toast.LENGTH_LONG).show()
            }
            bulanIni -> {
                Toast.makeText(this, "Bulan Ini Activated", Toast.LENGTH_LONG).show()
            }
            tahunIni -> {
                Toast.makeText(this, "Tahun Ini Activated", Toast.LENGTH_LONG).show()
            }
            date -> {
                Toast.makeText(this, "Date Activated", Toast.LENGTH_LONG).show()
            }
            else -> {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }
        }

        val intentResultMutasi = Intent(this, PinMutasiActivity::class.java)
        startActivity(intentResultMutasi)

    }

    override fun setTheDate(year: Int, month: Int, day: Int, source: String) {
        val monthName: String =
            when (month) {
                0 -> "Jan"
                1 -> "Feb"
                2 -> "Mar"
                3 -> "Apr"
                4 -> "Mei"
                5 -> "Jun"
                6 -> "Jul"
                7 -> "Agu"
                8 -> "Sep"
                9 -> "Okt"
                10 -> "Nov"
                11 -> "Des"
                else -> ""
            }

        if (source == "start") {
            tv_mutasi_start.text    = "$day $monthName $year"
        } else {
            tv_mutasi_end.text      = "$day $monthName $year"
        }

        checkButtonNextCondition()
    }

    override fun checkNextButton() {
        checkButtonNextCondition()
    }

    override fun getContext(): Context {
        return this
    }

    override fun showUserData(data: GetNasabah.Data) {
        tv_mutasi_username.text = data.namaLengkap
        tv_mutasi_rekening.text = data.noRekening
    }
}