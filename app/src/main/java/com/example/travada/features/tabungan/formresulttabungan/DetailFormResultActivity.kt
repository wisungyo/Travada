package com.example.travada.features.tabungan.formresulttabungan

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.BarengTemanAdapter
import com.example.travada.features.tabungan.adapter.TabungBarengAdapter
import com.example.travada.features.tabungan.formtabungandua.FormTabunganTwoPresenter
import com.example.travada.features.tabungan.maintabungan.TabunganActivity
import com.example.travada.features.tabungan.models.DataTabungBareng
import kotlinx.android.synthetic.main.activity_detail_form_result.*
import kotlinx.android.synthetic.main.activity_form_tabungan_two.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DetailFormResultActivity : AppCompatActivity(), DetailFormResultPresenter.Listener {
    private lateinit var presenter: DetailFormResultPresenter
    private lateinit var bundle: Bundle

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_form_result)

        presenter = DetailFormResultPresenter(this)
        intent?.extras?.let { bundle = it }
        btnBuatSekarang.setOnClickListener {
            val gotoMainTabungan = Intent(this, TabunganActivity::class.java)
            startActivity(gotoMainTabungan)
        }

        ivFormResultBack.setOnClickListener {
            finish()
        }

        val namaTujuan = bundle.getString("namaTujuan")
        tvNamaTujuan.setText(namaTujuan)

        val jumlahDitabung = bundle.getString("jumlahDitabung")
        tvJumlahDitabung.text = "Rp. ${jumlahDitabung}"

        val setoranAwal = bundle.getString("setoranAwal")
        tvSetoranAwal.text = "Rp. ${setoranAwal}"

        val tabunganBulanan = bundle.getString("jumlahSetoran")
        tvTabunganBulanan.text = "Rp. ${tabunganBulanan}"

        val tanggalTarget = bundle.getString("tanggalTarget")
        tvTanggalTarget.setText(tanggalTarget)
    }

    override fun showDataTabungBareng(adapterTabungBareng: BarengTemanAdapter) {
        val layoutManagerLinear =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvDetail.layoutManager = layoutManagerLinear
        rvDetail.adapter = adapterTabungBareng
        rvDetail.overScrollMode = View.OVER_SCROLL_NEVER
    }
}