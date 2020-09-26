package com.example.travada.features.rencana.wisnu.view

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.wisnu.adapter.AdapterKonfirmasiRencanaActivity
import com.example.travada.features.rencana.wisnu.presenter.KonfirmasiRencanaActivityPresenter
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_konfirmasi_rencana.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class KonfirmasiRencanaActivity : AppCompatActivity(), KonfirmasiRencanaActivityPresenter.Listener {
    private lateinit var presenter: KonfirmasiRencanaActivityPresenter
    private lateinit var progressDialog: ProgressDialog
//    val MyFragment= LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_rencana)
        presenter = KonfirmasiRencanaActivityPresenter(this)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Mohon tunggu...")

        val intentPosition = intent.getIntExtra("DESTINASI_ID", 3)
        val intentJumlahOrang = intent.getIntExtra("JUMLAH_ORANG", 1)
        val intentJumlahBiaya = intent.getIntExtra("JUMLAH_BIAYA", 0)

        presenter.fetchMainData(intentPosition, intentJumlahOrang)
        presenter.fetchDataCicilan(intentJumlahOrang, intentJumlahBiaya)
        presenter.fetchDataCicilanLayout()
        presenter.checkNextButtonCondition(intentJumlahOrang)

        iv_konfirmasi_rencana_back.setOnClickListener {
            presenter.doBack()
        }

        btn_konfirmas_rencana.setOnClickListener {
            presenter.nextButtonClicked()
        }
    }

    override fun showMainData(data: GetDestinasiResponse.Data, jumlahOrang: Int) {
        // get the image from API
        if (data.gambarList.isNotEmpty()) {
            Glide
                .with(this)
                .load(data.gambarList[0])
                .centerCrop()
                .into(iv_konfirmasi_rencana)
        } else {
            Glide
                .with(this)
                .load("https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
                .centerCrop()
                .into(iv_konfirmasi_rencana)
        }

        // get the title from API
        tv_konfirmasi_rencana_topbar.text = data.namaTrip
        tv_konfirmasi_rencana_title.text = data.namaTrip

        // get the people from API
        val intentJumlahOrang = intent.getIntExtra("JUMLAH_ORANG", 1)
        tv_konfirmasi_rencana_member.text = "Jumlah : ${intentJumlahOrang} orang"
        
        // get the schedule from API
        tv_konfirmasi_rencana_date.text = "${data.berangkat} - ${data.pulang}"

        // get the total member
        tv_konfirmasi_rencana_jumlah_orang.text = "${jumlahOrang} orang"
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

    override fun showDataError(localizedMessage: String?) {
        Toast.makeText(
            this,
            "Error : ${localizedMessage}",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showBack() {
        finish()
    }

    override fun showProgressDialog() {
        progressDialog.show()
//        val fm=supportFragmentManager
//        MyFragment.isCancelable = false
//        MyFragment.show(fm, "Fragment")
    }

    override fun dismissProgressDialog() {
        progressDialog.dismiss()
//        MyFragment.dismiss()
    }

}