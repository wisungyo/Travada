package com.example.travada.detailriwayat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.detailriwayat.adapter.AdapterSpinnerBayarCicilan
import com.example.travada.detailriwayat.adapter.CustomDropDownAdapter
import com.example.travada.detailriwayat.presenter.BayarCicilanActivityPresenter
import com.example.travada.features.rencana.wisnu.view.DialogKonfirmasiPemesanan
import com.example.travada.sampeldata.DataSpinnerCicilan
import com.example.travada.sampeldata.SaldoSpinnerData
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_bayar_cicilan.*

class BayarCicilanActivity : AppCompatActivity(), BayarCicilanActivityPresenter.Listener {
    private lateinit var presenter : BayarCicilanActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayar_cicilan)
        presenter = BayarCicilanActivityPresenter(this)

//        presenter.fetchData()

        val modelList: List<SaldoSpinnerData> = readFromAsset()

        val customDropDownAdapter = CustomDropDownAdapter(this, modelList)
        spinner_rencana_pesan.adapter = customDropDownAdapter

//        spinner_rencana_pesan.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//        }
//
//        btn_bayar_cicilan.setOnClickListener {
//            presenter.doKonfirmasi()
//        }
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

    fun showPinKonfirmasi() {
        val intentPinKonfirmasi = Intent(this, PinBayarCicilanActivity::class.java)
        startActivity((intentPinKonfirmasi))
    }

    override fun showSpinner(
        adapterSpinner: AdapterSpinnerBayarCicilan,
        linearLayout: LinearLayoutManager
    ) {
//        val adapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_spinner_item,
//            listSpinner
//        )
//        adapter.setDropDownViewResource(R.layout.cicilan_spinner_item)
//        spinner_rencana_pesan.adapter = adapter

//        spinner_rencana_pesan.adapter = adapterSpinner
//        spinner_rencana_pesan.
    }

    override fun showDialogKonfirmasi(title: String, subtitle: String) {
        DialogKonfirmasiBayarCicilan.newInstance(title, subtitle).show(supportFragmentManager, DialogKonfirmasiPemesanan.TAG)
    }
}