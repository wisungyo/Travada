package com.example.travada.features.rencana.detailrencana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse

import kotlinx.android.synthetic.main.activity_detail_rencana.*
import kotlinx.android.synthetic.main.detail_rencana_parent_layout.*
import kotlinx.android.synthetic.main.list_fasilitas_perjalanan.*
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
        //rvNestedDetailrencana.overScrollMode = View.OVER_SCROLL_NEVER

        presenter = DetailRencanaPresenter(this)
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

//        tvSelengkapnya.setOnClickListener {
//            btnSelengkapnyaDeskripsi()
//        }
//
//        tvSelengkapnya2.setOnClickListener {
//            btnSelengkapnyaPerjalanan()
//        }
//
//        btnPesanSekarangDetailRencana.setOnClickListener {
//            // ISI
//        }

    }

    //rvRencanaPerjalan.overScrollMode = View.OVER_SCROLL_NEVER
//    override fun btnSelengkapnyaDeskripsi() {
//        tvSelengkapnya.visibility = View.GONE
//        vGradient.visibility = View.GONE
//        tvDeskripsiKonten.setMaxLines(Integer.MAX_VALUE)
//    }
//
//    override fun btnSelengkapnyaPerjalanan() {
//        tvSelengkapnya2.visibility = View.GONE
//        vGradient2.visibility = View.GONE
//        rvRencanaPerjalan.adapter?.itemCount
//    }

    override fun implementDetailDestinasi(getDestinasi: MutableList<GetDestinasiDetailResponse.Data>) {
        setUpRecyclerViewNested(getDestinasi)
    }

    fun setUpRecyclerViewNested(listDetailRencana : List<GetDestinasiDetailResponse.Data>){
        rvNestedDetailrencana.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rvNestedDetailrencana.adapter = DetailRencanaAdapter(listDetailRencana, presenter)
    }




}