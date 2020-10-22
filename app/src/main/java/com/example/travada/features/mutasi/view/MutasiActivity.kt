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
import kotlin.properties.Delegates

class MutasiActivity : AppCompatActivity(), MutasiActivityPresenter.Listener {
    lateinit var presenter: MutasiActivityPresenter
    var namaLengkap by Delegates.notNull<String>()
    var rekening    by Delegates.notNull<String>()

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
        val intentResultMutasi = Intent(this, ResultMutasiActivity::class.java)
        when {
            mingguIni -> {
//                Toast.makeText(this, "Minggu Ini Activated", Toast.LENGTH_LONG).show()
                intentResultMutasi.putExtra("MUTASI", "minggu")
            }
            bulanIni -> {
//                Toast.makeText(this, "Bulan Ini Activated", Toast.LENGTH_LONG).show()
                intentResultMutasi.putExtra("MUTASI", "bulan")
            }
            tahunIni -> {
//                Toast.makeText(this, "Tahun Ini Activated", Toast.LENGTH_LONG).show()
                intentResultMutasi.putExtra("MUTASI", "tahun")
            }
            date -> {
//                Toast.makeText(this, "Date Activated", Toast.LENGTH_LONG).show()
                intentResultMutasi.putExtra("MUTASI", "tanggal")
                intentResultMutasi.putExtra("TGL_AWAL", tv_mutasi_start.text.toString())
                intentResultMutasi.putExtra("TGL_AKHIR", tv_mutasi_end.text.toString())
            }
            else -> {
//                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }
        }

        intentResultMutasi.putExtra("MUTASI_NAMA", namaLengkap)
        intentResultMutasi.putExtra("MUTASI_REK", rekening)
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

        namaLengkap = data.namaLengkap
        rekening    = data.noRekening
    }
}