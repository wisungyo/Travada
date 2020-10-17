package com.example.travada.fragmentnav.notifikasi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.wisnu.view.DialogKonfirmasiPemesanan
import com.example.travada.fragmentnav.notifikasi.pojo.GetDetailNotifikasiResponse
import com.example.travada.fragmentnav.notifikasi.presenter.DetailNotifikasiTravaplanPresenter
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_detail_notifikasi_travaplan.*
import kotlinx.android.synthetic.main.activity_detail_notifikasi_travaplan.iv_result_rencana
import kotlinx.android.synthetic.main.activity_detail_notifikasi_travaplan.tv_result_rencana_booking_date
import kotlinx.android.synthetic.main.activity_detail_notifikasi_travaplan.tv_result_rencana_id
import kotlinx.android.synthetic.main.activity_detail_notifikasi_travaplan.tv_result_rencana_member
import kotlinx.android.synthetic.main.activity_detail_notifikasi_travasave.btnBack
import kotlinx.android.synthetic.main.activity_detail_rencana.*
import kotlinx.android.synthetic.main.activity_detail_riwayat.*
import kotlinx.android.synthetic.main.activity_result_rencana.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class DetailNotifikasiTravaplan : AppCompatActivity(),DetailNotifikasiTravaplanPresenter.Listener {
    private lateinit var presenter: DetailNotifikasiTravaplanPresenter
    val MyFragment = LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_notifikasi_travaplan)
        val intentId = intent.getIntExtra("ID_Notifikasi", 0)

        presenter = DetailNotifikasiTravaplanPresenter(this)
        presenter.fetchDestinasiData(intentId)

        btnBack.setOnClickListener {
            finish()
        }
    }

    override fun implementDestinasi(getDestinasi: GetDetailNotifikasiResponse.Data.Destinasi?) {
        getDestinasi?.let {
            tv_result_notif_title.text = getDestinasi.namaTrip

            val berangkatTahun      = extractTahun(getDestinasi.berangkat[0].toString())
            val berangkatBulan      = extractBulan(getDestinasi.berangkat[0].toString())
            val namaBulanBerangkat:String  = changeBulan(berangkatBulan)
            val berangkatTanggal    = extractTanggal(getDestinasi.berangkat[0].toString())

            val pulangTahun         = extractTahun(getDestinasi.pulang[0].toString())
            val pulangBulan         = extractBulan(getDestinasi.pulang[0].toString())
            val namaBulanPulang:String      = changeBulan(pulangBulan)
            val pulangTanggal       = extractTanggal(getDestinasi.pulang[0].toString())

            if (namaBulanBerangkat == namaBulanPulang) {
                tv_result_rencana_date_notif.text =
                    "$berangkatTanggal $namaBulanBerangkat - $pulangTanggal $namaBulanPulang $pulangTahun"
            } else {
                tv_result_rencana_date_notif.text =
                    "$berangkatTanggal $namaBulanBerangkat $berangkatTahun - $pulangTanggal $namaBulanPulang $pulangTahun"
            }

            if (getDestinasi.gambarList.isNotEmpty()) {
                Glide
                    .with(this)
                    .load(getDestinasi.gambarList[0])
                    .centerCrop()
                    .into(iv_result_rencana)
            } else {
                Glide
                    .with(this)
                    .load( "https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
                    .centerCrop()
                    .into(iv_result_rencana)
            }
        }
    }

    override fun implementPemesanan(getPemesanan: GetDetailNotifikasiResponse.Data.Pemesanan?) {
        getPemesanan?.let {
            val df = DecimalFormat("#,###")
            df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)

            tv_result_rencana_pembayaran_notif.text = "Rp. ${df.format(getPemesanan.total)}"
            tv_result_rencana_member.text = "Jumlah : ${getPemesanan.orang} orang"

            tv_result_rencana_booking_date.text = getPemesanan.createdAt

            val dibuatTahun         =  extractTahun(getPemesanan.createdAt)
            val dibuatBulan         = extractBulan(getPemesanan.createdAt)
            val namaBulanDibuat     = changeBulan(dibuatBulan)
            val dibuatTanggal       =  extractTanggal(getPemesanan.createdAt)

            tv_result_rencana_booking_date.text = "$dibuatTanggal $namaBulanDibuat $dibuatTahun"

            tv_result_rencana_id.text = getPemesanan.id.toString()
        }


    }

    override fun showDialogGabung(titleGabung: String, subtitleGabung: String) {
        DialogKonfirmasiNotifikasiGabung.newInstance(titleGabung, subtitleGabung).show(supportFragmentManager, DialogKonfirmasiNotifikasiGabung.TAG)
    }

    override fun showDialogTolak(titleTolak: String, subtitleTolak: String) {
        DialogKonfirmasiNotifikasiTolak.newInstance(titleTolak, subtitleTolak).show(supportFragmentManager, DialogKonfirmasiNotifikasiTolak.TAG)
    }

    override fun showLoadingDialog() {
        val fm = supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun implementDetailRencanaFailure(errMessage: String) {
        Toast.makeText(this, "Error : $errMessage", Toast.LENGTH_LONG).show()
    }

    fun showKonfirmasiYesDialogGabung() {
//        presenter.postPemesananData()
    }

    fun showKonfirmasiYesDialogTolak() {
//        presenter.postPemesananData()
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
}