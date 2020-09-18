package com.example.travada.features.rencana.detailrencana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.rencana.detailrencana.DataInfoTambahan.dataInfoTambahan
import com.example.travada.features.rencana.detailrencana.DetailRencanaPresenter.Companion.INTENT_PARCELABEL
import com.example.travada.fragmentnav.riwayat.fragmentriwayat.ProsesFragmentPresenter

import kotlinx.android.synthetic.main.activity_detail_rencana.*

class DetailRencanaActivity : AppCompatActivity(),DetailRencanaPresenter.Listener {


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
        presenter.fetchFasilitasWisata()
        presenter.fetchGambarWisata()
        presenter.fetchRencanaPerjalanan()

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
            btnSelengkapnyaSatu()
        }

        tvSelengkapnya2.setOnClickListener {
            btnSelengkapnyaDua()
        }

        btnPesanSekarangDetailRencana.setOnClickListener {
            // ISI
        }

    }

    // tampil data gambar banner
    override fun showDataGambarWisata (
        adapterGambarWisata: GambarWisataAdapter,
        linearLayoutGambarWisata: LinearLayoutManager ) {
        rvDetailGambarWisata.layoutManager = linearLayoutGambarWisata
        rvDetailGambarWisata.adapter = adapterGambarWisata
        rvDetailGambarWisata.overScrollMode = View.OVER_SCROLL_NEVER
    }

    // tampil data fasilitas wisata
    override fun showDataFasilitasWisata (
        adapterFasilitasWIsata: FasilitasWisataAdapter,
        linearLayoutFasilitasWisata: LinearLayoutManager ) {

        rvFasilitasPerjalan.layoutManager = linearLayoutFasilitasWisata
        rvFasilitasPerjalan.adapter = adapterFasilitasWIsata
        rvFasilitasPerjalan.overScrollMode = View.OVER_SCROLL_NEVER
    }

    // tampil data rencana perjalanan
    override fun showDataRencanaPerjalanan(
        adapterRencanaPerjalanan: RencanaPerjalananAdapter,
        linearLayoutRencanaWisata: LinearLayoutManager ) {

        rvRencanaPerjalan.layoutManager = linearLayoutRencanaWisata
        rvRencanaPerjalan.adapter = adapterRencanaPerjalanan
        rvRencanaPerjalan.overScrollMode = View.OVER_SCROLL_NEVER
    }

    // tombol selengkapnya deskripsi wisata
    override fun btnSelengkapnyaSatu() {
        tvSelengkapnya.visibility = View.GONE
        vGradient.visibility = View.GONE
        tvDeskripsiKonten.setMaxLines(Integer.MAX_VALUE)
    }

    // tombol selengkapnya rencana perjalanan
    override fun btnSelengkapnyaDua() {
        tvSelengkapnya2.visibility = View.GONE
        vGradient2.visibility = View.GONE
        rvRencanaPerjalan.adapter?.itemCount
    }


}