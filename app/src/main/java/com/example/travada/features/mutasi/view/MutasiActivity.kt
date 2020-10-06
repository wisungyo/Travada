package com.example.travada.features.mutasi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R
import com.example.travada.features.mutasi.presenter.MutasiActivityPresenter
import kotlinx.android.synthetic.main.activity_mutasi.*

class MutasiActivity : AppCompatActivity(), MutasiActivityPresenter.Listener {
    lateinit var presenter: MutasiActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutasi)
        presenter = MutasiActivityPresenter(this)

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
}