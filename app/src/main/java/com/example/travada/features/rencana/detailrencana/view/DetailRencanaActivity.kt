package com.example.travada.features.rencana.detailrencana.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ExpandableListView.OnGroupClickListener
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.rencana.detailrencana.adapter.FasilitasWisataAdapter
import com.example.travada.features.rencana.detailrencana.adapter.GambarWisataAdapter
import com.example.travada.features.rencana.detailrencana.adapter.InfoTambahanAdapter
import com.example.travada.features.rencana.detailrencana.adapter.RencanaPerjalananAdapter
import com.example.travada.features.rencana.detailrencana.presenter.DetailRencanaPresenter
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.pesan.PesanRencanaActivity
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_detail_rencana.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.ArrayList


class DetailRencanaActivity : AppCompatActivity(), DetailRencanaPresenter.Listener {

    val header: MutableList<String> = ArrayList()
    val body: MutableList<MutableList<String>> = ArrayList()

    private lateinit var presenter: DetailRencanaPresenter
    val MyFragment = LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_rencana)
        // nestedView.overScrollMode = View.OVER_SCROLL_NEVER

        val expListView = elInfoTambahan as ExpandableListView
        val intentId = intent.getIntExtra("DESTINASI_ID", 3)

        presenter = DetailRencanaPresenter(this)
        presenter.getDetailRencana(intentId)

        ivBackDetailRencana.setOnClickListener {
            finish()
        }

        tvSelengkapnya.setOnClickListener {
            btnSelengkapnyaDeskripsi()
        }

        tvSelengkapnya2.setOnClickListener {
            btnSelengkapnyaPerjalanan()
        }

        btnPesanSekarangDetailRencana.setOnClickListener {
            val intentPesanRencana = Intent(this, PesanRencanaActivity::class.java)
            intentPesanRencana.putExtra("DESTINASI_ID", intentId)
            startActivity(intentPesanRencana)
        }

//        val param = expListView.getLayoutParams() as LinearLayout.LayoutParams
//        param.height = LinearLayout.LayoutParams.WRAP_CONTENT
//        expListView.setLayoutParams(param)
//        expListView.refreshDrawableState()

        expListView.setOnGroupClickListener(ExpandableListView.OnGroupClickListener { parent, v, groupPosition, id ->
            setListViewHeight(parent, groupPosition)
            false
        })

//        expListView.setOnGroupClickListener(OnGroupClickListener { parent, v, groupPosition, id ->
//            true // This way the expander cannot be collapsed
//        })
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

//        Glide.with(this).load(getDestinasi.gambarList[0]).into(ivDetailGambar)

        if (getDestinasi.gambarList.isNotEmpty()) {
            Glide
                .with(this)
                .load(getDestinasi.gambarList[0])
                .centerCrop()
                .into(ivDetailGambar)
        } else {
            Glide
                .with(this)
                .load("https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
                .centerCrop()
                .into(ivDetailGambar)
        }

        showListGambar(getDestinasi.gambarList)
        showListPerjalanan(getDestinasi.rencanaList)
        showListFasilitas(getDestinasi.fasilitas)

        showInfoSyaratKetentuan("${getDestinasi.syaratKetentuan}") // adding "" to handle null reference, in order not force-close
        showInfoPersiapan("${getDestinasi.infoPersiapan}") // adding "" to handle null reference, in order not force-close
        showInfoWaktuCuaca("${getDestinasi.infoWaktuCuaca}") // adding "" to handle null reference, in order not force-close

        elInfoTambahan.setAdapter(InfoTambahanAdapter(this, elInfoTambahan, header, body))

        ivBackDetailRencana.setOnClickListener {
            finish()
        }
    }

    override fun showInfoSyaratKetentuan(syaratKetentuan: String) {
        val infoSyaratKetentuan: MutableList<String> = ArrayList()
        infoSyaratKetentuan.add(syaratKetentuan)
        body.add(infoSyaratKetentuan)
        header.add("Syarat & Ketentuan")
    }

    override fun showInfoPersiapan(keberangkatan: String) {
        val infoPersiapan: MutableList<String> = ArrayList()
        infoPersiapan.add(keberangkatan)
        body.add(infoPersiapan)
        header.add("Persiapan sebelum berangkat")
    }

    override fun showInfoWaktuCuaca(waktuCuaca: String) {
        val infoWaktuCuaca: MutableList<String> = ArrayList()
        infoWaktuCuaca.add(waktuCuaca)
        body.add(infoWaktuCuaca)
        header.add("Waktu & Cuaca")
    }

    override fun showListGambar(gambarList: List<String>) {
        rvDetailGambarWisata.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvDetailGambarWisata.adapter =
            GambarWisataAdapter(
                gambarList,
                presenter
            )
        rvDetailGambarWisata.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun showGambarDetail(detailGambar: String) {
        Glide.with(this).load(detailGambar).into(ivDetailGambar)
    }

    override fun showListFasilitas(fasilitasList: List<String>) {
        rvFasilitasPerjalan.layoutManager =
            GridLayoutManager(this, 2)
        rvFasilitasPerjalan.adapter = FasilitasWisataAdapter(fasilitasList, presenter)
        rvFasilitasPerjalan.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun showListPerjalanan(PerjalananList: List<String>) {
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
        val lp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        rvRencanaPerjalan.setLayoutParams(lp)
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

    private fun setListViewHeight(expendalbeInfo: ExpandableListView, group: Int) {
        val listAdapter = expendalbeInfo.expandableListAdapter as ExpandableListAdapter
        var totalHeight = 0
        val desiredWidth = View.MeasureSpec.makeMeasureSpec(
            expendalbeInfo.width,
            View.MeasureSpec.EXACTLY
        )
        for (i in 0 until listAdapter.groupCount) {
            val groupItem = listAdapter.getGroupView(i, false, null, expendalbeInfo)
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
            totalHeight += groupItem.measuredHeight
            if (expendalbeInfo.isGroupExpanded(i) && i != group || !expendalbeInfo.isGroupExpanded(i) && i == group) {
                for (j in 0 until listAdapter.getChildrenCount(i)) {
                    val listItem = listAdapter.getChildView(
                        i, j, false, null,
                        expendalbeInfo
                    )
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
                    totalHeight += listItem.measuredHeight
                }
            }
        }
        val params = expendalbeInfo.layoutParams
        var height = (totalHeight + expendalbeInfo.dividerHeight * (listAdapter.groupCount - 1))
        if (height < 10) height = 200
        params.height = height
        expendalbeInfo.layoutParams = params
        expendalbeInfo.requestLayout()
    }
}