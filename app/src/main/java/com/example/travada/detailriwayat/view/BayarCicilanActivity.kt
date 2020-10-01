package com.example.travada.detailriwayat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R
import com.example.travada.detailriwayat.adapter.CustomDropDownAdapter
import com.example.travada.detailriwayat.presenter.BayarCicilanActivityPresenter
import com.example.travada.features.rencana.wisnu.view.DialogKonfirmasiPemesanan
import com.example.travada.sampeldata.SaldoSpinnerData
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_bayar_cicilan.*

class BayarCicilanActivity : AppCompatActivity(), BayarCicilanActivityPresenter.Listener {
    private lateinit var presenter : BayarCicilanActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayar_cicilan)
        presenter = BayarCicilanActivityPresenter(this)

        val modelList: List<SaldoSpinnerData> = readFromAsset()

        val customDropDownAdapter = CustomDropDownAdapter(this, modelList)
        spinner_rencana_pesan.adapter = customDropDownAdapter

        btn_bayar_cicilan.setOnClickListener {
            presenter.doKonfirmasi()
        }
    }

    private fun readFromAsset(): List<SaldoSpinnerData> {
        val file_name = "android_version.json"

        val bufferReader = application.assets.open(file_name).bufferedReader()

        val json_string = bufferReader.use {
            it.readText()
        }
        val gson = Gson()
        val modelList: List<SaldoSpinnerData> = gson.fromJson(json_string, Array<SaldoSpinnerData>::class.java).toList()
        return modelList
    }

    override fun showDialogKonfirmasi(title: String, subtitle: String) {
        DialogKonfirmasiBayarCicilan.newInstance(title, subtitle).show(supportFragmentManager, DialogKonfirmasiPemesanan.TAG)
    }
}