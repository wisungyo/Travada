package com.example.travada.features.rencana.detailrencana

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import kotlinx.android.synthetic.main.activity_detail_rencana.*
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

        // bundle PR , sekalian mau di kasih API
        // val bundle = Bundle()
        //bundle.putParcelable(INTENT_PARCELABEL, data)

//        val fragment = GambarWisataFragment()
//        fragment.setArguments(bundle)
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frameFragment, fragment)
//        fragmentTransaction.commit()

        presenter = DetailRencanaPresenter(this)
        presenter.fetchRencanaPerjalanan()
        presenter.getDetailRencana()

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

    override fun showDataRencanaPerjalanan(
        adapterRencanaPerjalanan: RencanaPerjalananAdapter,
        linearLayoutRencanaWisata: LinearLayoutManager
    ) {

        rvRencanaPerjalan.layoutManager = linearLayoutRencanaWisata
        rvRencanaPerjalan.adapter = adapterRencanaPerjalanan
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

        val fasilitas = getDestinasi.fasilitas
        rvFasilitasPerjalan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rvFasilitasPerjalan.adapter = FasilitasWisataAdapter(fasilitas,presenter)

        val gambar = getDestinasi.gambarList
        rvDetailGambarWisata.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rvDetailGambarWisata.adapter = GambarWisataAdapter(gambar,presenter)
        rvDetailGambarWisata.overScrollMode = View.OVER_SCROLL_NEVER

    }

}