package com.example.travada.features.rencana.detailrencana

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.detailriwayat.DetailRiwayatActivity
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import kotlinx.android.synthetic.main.activity_detail_rencana.*
import kotlinx.android.synthetic.main.list_gambar_wisata.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.ArrayList

class DetailRencanaActivity : AppCompatActivity(), DetailRencanaPresenter.Listener {

    private var expandableListView: ExpandableListView? = null
    private var adapter: ExpandableListAdapter? = null
    private var titleList: List<String>? = null
    private lateinit var presenter: DetailRencanaPresenter

    //private lateinit var data: DataGambarWisata

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_rencana)
        nestedView.overScrollMode = View.OVER_SCROLL_NEVER


        presenter = DetailRencanaPresenter(this)
        presenter.getDetailRencana(15)

        // info tambahan
        expandableListView = findViewById(R.id.expendableList)
        if (expandableListView != null) {
            val listData = DataInfoTambahan.dataInfoTambahan
            titleList = ArrayList(listData.keys)
            adapter = InfoTambahanAdapter(this, titleList as ArrayList<String>, listData)
            expandableListView!!.setAdapter(adapter)

            expandableListView!!.setOnGroupExpandListener { groupPosition ->
                Toast.makeText(
                    applicationContext,
                    (titleList as ArrayList<String>)[groupPosition] + " List Expanded.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                Toast.makeText(
                    applicationContext,
                    (titleList as ArrayList<String>)[groupPosition] + " List Collapsed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            expandableListView!!.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
                Toast.makeText(
                    applicationContext,
                    "Clicked: " + (titleList as ArrayList<String>)[groupPosition] + " -> " + listData[(
                            titleList as
                                    ArrayList<String>
                            )
                            [groupPosition]]!!.get(
                        childPosition
                    ),
                    Toast.LENGTH_SHORT
                ).show()
                false
            }
        }

        tvSelengkapnya.setOnClickListener {
            btnSelengkapnyaDeskripsi()
        }

        tvSelengkapnya2.setOnClickListener {
            btnSelengkapnyaPerjalanan()
        }

        btnPesanSekarangDetailRencana.setOnClickListener {
            // ISI
        }

    }

    override fun implementDetailDestinasi(getDestinasi: GetDestinasiDetailResponse.Data) {
        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)

        tvTitleDetailRencana.text = getDestinasi.namaTrip
        tvHeadingDetailRencana.text = getDestinasi.namaTrip
        tvBenua.text = getDestinasi.benua
        tvWaktu.text = "${getDestinasi.durasi} hari"
        tvPeserta.text = "${getDestinasi.kapasitas} orang"
        tvOverviewKonten.text = getDestinasi.overview
        tvDeskripsiKonten.text = getDestinasi.deskripsi
        tvBiayaDetailRencana.text = "Rp. ${df.format(getDestinasi.hargaSatuan)}"

        Glide.with(this).load(getDestinasi.gambarList[0]).into(ivDetailGambar)

        listGambar(getDestinasi.gambarList)
        listPerjalanan(getDestinasi.rencanaList)
        listFasilitas(getDestinasi.fasilitas)

    }

    override fun listGambar(gambarList: List<String>) {
        rvDetailGambarWisata.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvDetailGambarWisata.adapter = GambarWisataAdapter(gambarList, presenter)
        rvDetailGambarWisata.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun gambarDetail(detailGambar: String) {
        Glide.with(this).load(detailGambar).into(ivDetailGambar)
    }

    override fun listFasilitas(fasilitasList: List<String>) {
        rvFasilitasPerjalan.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvFasilitasPerjalan.adapter = FasilitasWisataAdapter(fasilitasList, presenter)
        rvFasilitasPerjalan.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun listPerjalanan(PerjalananList: List<String>) {
        rvRencanaPerjalan.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvRencanaPerjalan.adapter = RencanaPerjalananAdapter(PerjalananList, presenter)
        rvRencanaPerjalan.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun btnSelengkapnyaDeskripsi() {
        tvSelengkapnya.visibility = View.GONE
        vGradient.visibility = View.GONE
        tvDeskripsiKonten.setMaxLines(Integer.MAX_VALUE)
    }

    override fun btnSelengkapnyaPerjalanan() {
        tvSelengkapnya2.visibility = View.GONE
        vGradient2.visibility = View.GONE
        rvRencanaPerjalan.adapter?.itemCount
    }
}