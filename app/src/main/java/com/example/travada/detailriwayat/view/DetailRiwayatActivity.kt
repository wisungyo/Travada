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
import com.example.travada.detailriwayat.adapter.AdapterDetailRiwayatActivityMenunggu
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.pojo.GetPemesananDestinasiResponse
import com.example.travada.features.rencana.pojo.GetPemesananDetailResponse
import com.example.travada.sampeldata.DataRiwayat
import kotlinx.android.synthetic.main.activity_detail_riwayat.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class DetailRiwayatActivity : AppCompatActivity(), DetailRiwayatActivityPresenter.Listener {
    private lateinit var presenter: DetailRiwayatActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_riwayat)
        presenter = DetailRiwayatActivityPresenter(this)
        val idDestinasi = intent.getIntExtra("ID_DESTINASI", 1)
        val idPemesanan = intent.getIntExtra("ID_PEMESANAN", 1)

        presenter.fetchDestinasiData(idDestinasi, idPemesanan)
        presenter.fetchCicilanData(idPemesanan)

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
            tv_detail_riwayat_date.text = "${data.berangkat} - ${data.pulang}"
            tv_detail_riwayat_booking_date.text = data.createdAt
        }
    }

    override fun showPemesananDataOnDestinasiData(data: GetPemesananDetailResponse.Data?) {
        if (data != null) {
            tv_detail_riwayat_member.text = "Jumlah: ${data.pemesanan.orang} orang" // edit this later to TOTAL PEMESAN

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