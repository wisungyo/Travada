package com.example.travada.detailriwayat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetPemesananDestinasiResponse
import com.example.travada.sampeldata.DataRiwayat
import kotlinx.android.synthetic.main.activity_detail_riwayat.*

class DetailRiwayatActivity : AppCompatActivity(), DetailRiwayatActivityPresenter.Listener {
    private lateinit var presenter: DetailRiwayatActivityPresenter
    private lateinit var result: DataRiwayat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_riwayat)
        presenter = DetailRiwayatActivityPresenter(this)
        val idDestinasi = intent.getIntExtra("ID_DESTINASI", 1)

        presenter.fetchDestinasiData(idDestinasi)
        presenter.fetchCicilanData()

        iv_detail_riwayat_back.setOnClickListener {
            finish()
        }
    }

    override fun showData(
        adapterDetailRiwayatActivity: AdapterDetailRiwayatActivity,
        linearLayoutDetailRiwayatActivity: LinearLayoutManager
    ) {
        rv_detail_riwayat.adapter = adapterDetailRiwayatActivity
        rv_detail_riwayat.layoutManager = linearLayoutDetailRiwayatActivity
    }

    override fun showDestinasiData(data: GetPemesananDestinasiResponse.Data?) {
//        if (data != null) {
//            tv_detail_riwayat_topbar.text = data.
//
//            if (data.gambarList.isNotEmpty()) {
//                Glide
//                    .with(this)
//                    .load(data.gambarList[0])
//                    .centerCrop()
//                    .into(iv_detail_riwayat)
//            } else {
//                Glide
//                    .with(this)
//                    .load("https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
//                    .centerCrop()
//                    .into(iv_detail_riwayat)
//            }
//
//            tv_detail_riwayat_title.text = data.namaTrip
//            tv_detail_riwayat_member.text = "Jumlah: ${data.kapasitas} orang" // edit this later to TOTAL PEMESAN
//            tv_detail_riwayat_date.text = "${data.berangkat} - ${data.pulang}"
//            tv_detail_riwayat_booking_date.text = data.createdAt
//            tv_detail_riwayat_id.text = data.benua // edit this later to ID PEMESANAN
//            tv_detail_riwayat_pembayaran.text = data.benua // edit this later to TOTAL BIAYA
//        }
    }
}