package com.example.travada.detailriwayat.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travada.R
import com.example.travada.detailriwayat.adapter.CustomDropDownAdapter
import com.example.travada.detailriwayat.presenter.BayarCicilanActivityPresenter
import com.example.travada.features.rencana.dialog.DialogKonfirmasiPemesanan
import com.example.travada.sampeldata.SaldoSpinnerData
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_bayar_cicilan.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.math.abs
import kotlin.properties.Delegates


class BayarCicilanActivity : AppCompatActivity(), BayarCicilanActivityPresenter.Listener, AdapterView.OnItemSelectedListener {
    private lateinit var presenter : BayarCicilanActivityPresenter
    var selectedAmount by Delegates.notNull<Int>()
    private var id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayar_cicilan)
        presenter = BayarCicilanActivityPresenter(this)
        val idCicilan = intent.getIntExtra("ID_CICILAN", 0)
        val jumlah = intent.getIntExtra("JUMLAH", 0)

        val spin = findViewById<View>(R.id.spinner_rencana_pesan) as Spinner
        spin.onItemSelectedListener = this

        fetchTagihan(jumlah)
        presenter.fetchData(idCicilan)

//        val modelList: List<SaldoSpinnerData> = readFromAsset()
//        val customDropDownAdapter = CustomDropDownAdapter(this, modelList)
//        spinner_rencana_pesan.adapter = customDropDownAdapter

        btn_bayar_cicilan.setOnClickListener {
            if (selectedAmount >= jumlah) {
                presenter.doKonfirmasi(idCicilan)
            } else {
                Toast.makeText(this, "Saldo tidak cukup", Toast.LENGTH_LONG).show()
            }
        }

        iv_bayar_cicilan_back.setOnClickListener {
            finish()
        }
    }

    fun fetchTagihan(jumlah: Int) {
        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        tv_bayar_cicilan_saldo.text = "Rp ${df.format(abs(jumlah))}"
    }

    override fun readFromAsset(): List<SaldoSpinnerData> {
        val file_name = "android_version.json"

        val bufferReader = application.assets.open(file_name).bufferedReader()

        val json_string = bufferReader.use {
            it.readText()
        }
        val gson = Gson()
        val modelList: List<SaldoSpinnerData> = gson.fromJson(
            json_string,
            Array<SaldoSpinnerData>::class.java
        ).toList()
        return modelList
    }

    fun showPinKonfirmasi() {
        val idCicilan = intent.getIntExtra("ID_CICILAN", 0)
        val intentPinKonfirmasi = Intent(this, PinBayarCicilanActivity::class.java)
        intentPinKonfirmasi.putExtra("ID_CICILAN", idCicilan)
        startActivity(intentPinKonfirmasi)
    }

    override fun showSpinner(list: List<SaldoSpinnerData>) {
        Log.d("SPINNER", "${list.size}-${list[0].jumlah}")
        val customDropDownAdapter = CustomDropDownAdapter(this, list)
        spinner_rencana_pesan.adapter = customDropDownAdapter

    }

    override fun showDialogKonfirmasi(title: String, subtitle: String) {
        DialogKonfirmasiBayarCicilan.newInstance(title, subtitle).show(
            supportFragmentManager,
            DialogKonfirmasiPemesanan.TAG
        )
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        Toast.makeText(
//            this,
//            spinner_rencana_pesan.adapter.getItem(p2).toString(),
//            Toast.LENGTH_LONG
//        ).show()
        val amount = spinner_rencana_pesan.adapter.getItem(p2).toString()
        selectedAmount = amount.toInt()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
//        Toast.makeText(
//            this,
//            spinner_rencana_pesan.adapter.getItem(0).toString(),
//            Toast.LENGTH_LONG
//        ).show()
    }
}