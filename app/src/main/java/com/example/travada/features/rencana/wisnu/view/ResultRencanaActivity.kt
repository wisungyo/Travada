package com.example.travada.features.rencana.wisnu.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.wisnu.presenter.ResultRencanaActivityPresenter
import com.example.travada.mainpage.MainPageActivity
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_result_rencana.*
import kotlinx.android.synthetic.main.activity_result_rencana.btn_konfirmas_rencana
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class ResultRencanaActivity : AppCompatActivity(), ResultRencanaActivityPresenter.Listener {
    private lateinit var presenter: ResultRencanaActivityPresenter
    val MyFragment= LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_rencana)
        presenter = ResultRencanaActivityPresenter(this)
        val idDestinasi = intent.getIntExtra("ID_DESTINASI", 3)

        presenter.fetchData(idDestinasi)

        btn_konfirmas_rencana.setOnClickListener {
            val intentHome = Intent(this, MainPageActivity::class.java)
            startActivity(intentHome)
        }
    }

    override fun showMainData(data: GetDestinasiResponse.Data) {
        val idPemesanan = intent.getIntExtra("ID_PEMESANAN", 1)
        val orang = intent.getIntExtra("ORANG", 1)
        val total = intent.getIntExtra("TOTAL",0)
        val tglPemesanan = intent.getStringExtra("TGL_PEMESANAN")

        if (data.gambarList.isNotEmpty()) {
            Glide
                .with(this)
                .load(data.gambarList[0])
                .centerCrop()
                .into(iv_result_rencana)
        } else {
            Glide
                .with(this)
                .load( "https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
                .centerCrop()
                .into(iv_result_rencana)
        }

        tv_result_rencana_title.text = data.namaTrip
        tv_result_rencana_member.text = "Jumlah: ${orang} orang"

        val berangkatTahun      = extractTahun(data.berangkat[0].toString())
        val berangkatBulan      = extractBulan(data.berangkat[0].toString())
        val namaBulanBerangkat:String  = changeBulan(berangkatBulan)
        val berangkatTanggal    = extractTanggal(data.berangkat[0].toString())

        val pulangTahun         = extractTahun(data.pulang[0].toString())
        val pulangBulan         = extractBulan(data.pulang[0].toString())
        val namaBulanPulang:String     = changeBulan(pulangBulan)
        val pulangTanggal       = extractTanggal(data.pulang[0].toString())

        if (namaBulanBerangkat == namaBulanPulang) {
            tv_result_rencana_date.text =
                "$berangkatTanggal $namaBulanBerangkat - $pulangTanggal $namaBulanPulang $pulangTahun"
        } else {
            tv_result_rencana_date.text =
                "$berangkatTanggal $namaBulanBerangkat $berangkatTahun - $pulangTanggal $namaBulanPulang $pulangTahun"
        }

        val dibuatTahun         = tglPemesanan?.let { extractTahun(it) }
        val dibuatBulan         = tglPemesanan?.let { extractBulan(it) }
        val namaBulanDibuat     = dibuatBulan?.let { changeBulan(it) }
        val dibuatTanggal       = tglPemesanan?.let { extractTanggal(it) }

        tv_result_rencana_booking_date.text =
            "$dibuatTanggal $namaBulanDibuat $dibuatTahun"

        tv_result_rencana_id.text = idPemesanan.toString()

        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        tv_result_rencana_pembayaran.text = "Rp. ${df.format(total)}"
    }

    fun extractTanggal(tanggal: String): String {
        return tanggal.subSequence(8,10).toString()
    }

    fun extractBulan(bulan: String): String {
        return bulan.subSequence(5,7).toString()
    }

    fun extractTahun(tahun: String): String {
        return tahun.subSequence(0,4).toString()
    }

    fun changeBulan(bulan: String): String {
        var namaBulan = ""
        when (bulan) {
            "01" -> namaBulan = "Januari"
            "02" -> namaBulan = "Februari"
            "03" -> namaBulan = "Maret"
            "04" -> namaBulan = "April"
            "05" -> namaBulan = "Mei"
            "06" -> namaBulan = "Juni"
            "07" -> namaBulan = "Juli"
            "08" -> namaBulan = "Agustus"
            "09" -> namaBulan = "September"
            "10" -> namaBulan = "Oktober"
            "11" -> namaBulan = "November"
            "12" -> namaBulan = "Desember"
        }
        return namaBulan
    }

    override fun showDataError(error: String) {
        Toast.makeText(
            this,
            "Error : $error",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showLoadingDialog() {
        val fm=supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }
}