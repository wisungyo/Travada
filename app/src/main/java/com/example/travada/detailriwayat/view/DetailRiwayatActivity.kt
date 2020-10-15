package com.example.travada.detailriwayat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.detailriwayat.presenter.DetailRiwayatActivityPresenter
import com.example.travada.detailriwayat.adapter.AdapterDetailRiwayatActivityDisetujui
import com.example.travada.detailriwayat.adapter.AdapterDetailRiwayatActivityDitolak
import com.example.travada.detailriwayat.adapter.AdapterDetailRiwayatActivityExpired
import com.example.travada.detailriwayat.adapter.AdapterDetailRiwayatActivityMenunggu
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.pojo.GetPemesananDetailResponse
import kotlinx.android.synthetic.main.activity_detail_riwayat.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class DetailRiwayatActivity : AppCompatActivity(), DetailRiwayatActivityPresenter.Listener {
    private lateinit var presenter: DetailRiwayatActivityPresenter

    object ids {
        var idDestinasi = 0
        var idPemesanan = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_riwayat)
        presenter = DetailRiwayatActivityPresenter(this)
        val idDestinasi = intent.getIntExtra("ID_DESTINASI", 0)
        val idPemesanan = intent.getIntExtra("ID_PEMESANAN", 0)

        if (idDestinasi != 0) {
            ids.idDestinasi = idDestinasi
        }
        if (idPemesanan != 0) {
            ids.idPemesanan = idPemesanan
        }

        presenter.fetchDestinasiData(ids.idDestinasi, ids.idPemesanan)
        presenter.fetchCicilanData(ids.idPemesanan)

        iv_detail_riwayat_back.setOnClickListener {
            finish()
        }
    }

    override fun showData(
        adapterDetailRiwayatActivity: AdapterDetailRiwayatActivityDisetujui,
        linearLayoutDetailRiwayatActivity: LinearLayoutManager
    ) {
        rv_detail_riwayat.adapter = adapterDetailRiwayatActivity
        rv_detail_riwayat.layoutManager = linearLayoutDetailRiwayatActivity
    }

    override fun showData(
        adapterDetailRiwayatActivity: AdapterDetailRiwayatActivityDitolak,
        linearLayoutDetailRiwayatActivity: LinearLayoutManager
    ) {
        rv_detail_riwayat.adapter = adapterDetailRiwayatActivity
        rv_detail_riwayat.layoutManager = linearLayoutDetailRiwayatActivity
    }

    override fun showData(
        adapterDetailRiwayatActivity: AdapterDetailRiwayatActivityMenunggu,
        linearLayoutDetailRiwayatActivity: LinearLayoutManager
    ) {
        rv_detail_riwayat.adapter = adapterDetailRiwayatActivity
        rv_detail_riwayat.layoutManager = linearLayoutDetailRiwayatActivity
    }

    override fun showData(
        adapterDetailRiwayatActivity: AdapterDetailRiwayatActivityExpired,
        linearLayoutDetailRiwayatActivity: LinearLayoutManager
    ) {
        rv_detail_riwayat.adapter = adapterDetailRiwayatActivity
        rv_detail_riwayat.layoutManager = linearLayoutDetailRiwayatActivity
    }

    override fun showDestinasiData(data: GetDestinasiResponse.Data?) {
        if (data != null) {
            tv_detail_riwayat_topbar.text = data.namaTrip

            if (data.gambarList.isNotEmpty()) {
                Glide
                    .with(this)
                    .load(data.gambarList[0])
                    .centerCrop()
                    .into(iv_detail_riwayat)
            } else {
                Glide
                    .with(this)
                    .load("https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
                    .centerCrop()
                    .into(iv_detail_riwayat)
            }

            tv_detail_riwayat_title.text = data.namaTrip

            val berangkatTahun      = extractTahun(data.berangkat[0].toString())
            val berangkatBulan      = extractBulan(data.berangkat[0].toString())
            val namaBulanBerangkat:String  = changeBulan(berangkatBulan)
            val berangkatTanggal    = extractTanggal(data.berangkat[0].toString())

            val pulangTahun         = extractTahun(data.pulang[0].toString())
            val pulangBulan         = extractBulan(data.pulang[0].toString())
            val namaBulanPulang:String     = changeBulan(pulangBulan)
            val pulangTanggal       = extractTanggal(data.pulang[0].toString())

            if (namaBulanBerangkat == namaBulanPulang) {
                tv_detail_riwayat_date.text =
                    "$berangkatTanggal $namaBulanBerangkat - $pulangTanggal $namaBulanPulang $pulangTahun"
            } else {
                tv_detail_riwayat_date.text =
                    "$berangkatTanggal $namaBulanBerangkat $berangkatTahun - $pulangTanggal $namaBulanPulang $pulangTahun"
            }

            tv_detail_riwayat_booking_date.text = data.createdAt
        }
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

    override fun showPemesananDataOnDestinasiData(data: GetPemesananDetailResponse.Data?) {
        if (data != null) {
            tv_detail_riwayat_member.text = "Jumlah: ${data.pemesanan.orang} orang"

            val dibuatTahun         = extractTahun(data.pemesanan.createdAt)
            val dibuatBulan         = extractBulan(data.pemesanan.createdAt)
            val namaBulanDibuat     = changeBulan(dibuatBulan)
            val dibuatTanggal       = extractTanggal(data.pemesanan.createdAt)

            tv_detail_riwayat_booking_date.text =
                "$dibuatTanggal $namaBulanDibuat $dibuatTahun"

            tv_detail_riwayat_id.text = data.pemesanan.id.toString()

            val df = DecimalFormat("#,###")
            df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
            tv_detail_riwayat_pembayaran.text = "Rp. ${df.format(data.pemesanan.total)}"
        }
    }

    override fun showBayarCicilan() {
        val intentBayarCicilan = Intent(this, BayarCicilanActivity::class.java)
        startActivity(intentBayarCicilan)
    }
}