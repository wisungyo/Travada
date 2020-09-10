package com.example.travada.detailriwayat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.sampeldata.DataRiwayat
import kotlinx.android.synthetic.main.activity_detail_berita.*
import kotlinx.android.synthetic.main.activity_detail_riwayat.*

class DetailRiwayatActivity : AppCompatActivity(), DetailRiwayatActivityPresenter.Listener {
    private lateinit var presenter: DetailRiwayatActivityPresenter
    private lateinit var result: DataRiwayat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_riwayat)
        presenter = DetailRiwayatActivityPresenter(this)
        intent.getParcelableExtra<DataRiwayat>("RIWAYAT")?.let {
            result = it
        }

        fetchOverviewData()
        presenter.fetchCicilanData()

        iv_detail_riwayat_back.setOnClickListener {
            finish()
        }
    }

    private fun fetchOverviewData() {
        tv_detail_riwayat_topbar.text = result.title
        iv_detail_riwayat.setBackgroundResource(result.img)
        tv_detail_riwayat_title.text = result.title
        tv_detail_riwayat_member.text = "Jumlah: ${result.member} orang"
        tv_detail_riwayat_date.text = "${result.startDate} - ${result.endDate}"
        tv_detail_riwayat_booking_date.text = result.bookingDate
        tv_detail_riwayat_id.text = result.id
        tv_detail_riwayat_pembayaran.text = result.money
    }

    override fun showData(
        adapterDetailRiwayatActivity: AdapterDetailRiwayatActivity,
        linearLayoutDetailRiwayatActivity: LinearLayoutManager
    ) {
        rv_detail_riwayat.adapter = adapterDetailRiwayatActivity
        rv_detail_riwayat.layoutManager = linearLayoutDetailRiwayatActivity
    }
}