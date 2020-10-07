package com.example.travada.features.mutasi.view

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.travada.R
import com.example.travada.features.mutasi.presenter.MutasiActivityPresenter
import kotlinx.android.synthetic.main.activity_mutasi.*
import java.util.*

class MutasiActivity : AppCompatActivity(), MutasiActivityPresenter.Listener {
    lateinit var presenter: MutasiActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutasi)
        presenter = MutasiActivityPresenter(this)

        tv_mutasi_start.setOnClickListener {
            Log.d("TANGGAL", tv_mutasi_start.text.toString())
            presenter.getTheDate(this, "start", tv_mutasi_start.text.toString())
        }

        tv_mutasi_end.setOnClickListener {
            Log.d("TANGGAL", tv_mutasi_end.text.toString())
            presenter.getTheDate(this, "end", tv_mutasi_end.text.toString())
        }

        tv_mutasi_minggu_ini.setOnClickListener {
            presenter.goToResultMutasi()
        }

        tv_mutasi_bulan_ini.setOnClickListener {
            presenter.goToResultMutasi()
        }

        tv_mutasi_tahun_ini.setOnClickListener {
            presenter.goToResultMutasi()
        }

        btn_mutasi.setOnClickListener {
            presenter.goToResultMutasi()
        }
    }

    override fun showResultMutasi() {
        val intentResultMutasi = Intent(this, ResultMutasiActivity::class.java)
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
            tv_mutasi_start.text = "$day $monthName $year"
        } else {
            tv_mutasi_end.text = "$day $monthName $year"
        }
    }
}