package com.example.travada.features.rencana.detailrencana

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import kotlinx.android.synthetic.main.activity_detail_rencana.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.ArrayList

class DetailRencanaActivity : AppCompatActivity(), DetailRencanaPresenter.Listener {

    val header: MutableList<String> = ArrayList()
    val body: MutableList<MutableList<String>> = ArrayList()
    private lateinit var presenter: DetailRencanaPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_rencana)
        nestedView.overScrollMode = View.OVER_SCROLL_NEVER

        presenter = DetailRencanaPresenter(this)
        presenter.getDetailRencana(93)

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

        infoSyaratKetentuan(getDestinasi.syaratKetentuan)
        infoPersiapan(getDestinasi.infoPersiapan)
        infoWaktuCuaca(getDestinasi.infoWaktuCuaca)
        elInfoTambahan.setAdapter(InfoTambahanAdapter(this,elInfoTambahan, header, body))
    }

    override fun infoSyaratKetentuan(syaratKetentuan: String) {
        val infoSyaratKetentuan: MutableList<String> = ArrayList()
        infoSyaratKetentuan.add(syaratKetentuan)
        body.add(infoSyaratKetentuan)
        header.add("Syarat & Ketentuan")
    }

    override fun infoPersiapan(keberangkatan: String) {
        val infoPersiapan: MutableList<String> = ArrayList()
        infoPersiapan.add(keberangkatan)
        body.add(infoPersiapan)
        header.add("Persiapan sebelum berangkat")
    }

    override fun infoWaktuCuaca(waktuCuaca: String) {
        val infoWaktuCuaca: MutableList<String> = ArrayList()
        infoWaktuCuaca.add(waktuCuaca)
        header.add("Waktu & Cuaca")
        body.add(infoWaktuCuaca)
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
        //rvRencanaPerjalan.adapter?.itemCount
    }
}