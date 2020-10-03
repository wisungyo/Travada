package com.example.travada.fragmentnav.riwayat.fragmentriwayat

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.fragmentnav.riwayat.adapter.AdapterRiwayatStatus
import com.example.travada.detailriwayat.view.DetailRiwayatActivity
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.fragmentnav.riwayat.pojo.GetPemesananRiwayatResponse
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.fragment_riwayat_item.view.*
import kotlinx.android.synthetic.main.fragment_riwayat_status.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class StatusFragment : Fragment(), StatusFragmentPresenter.Listener, StatusFragmentPresenter.ListenerAdapter {
    private lateinit var presenter: StatusFragmentPresenter
    val MyFragment= LoadingDialog()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_riwayat_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = StatusFragmentPresenter(this, this)
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchDataRiwayat()
    }

    override fun showData(list: List<GetPemesananRiwayatResponse.Data>) {
        val adapterRiwayatStatus        = AdapterRiwayatStatus(list, presenter, this)
        val linearLayoutRiwayatStatus   = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_riwayat_status.adapter       = adapterRiwayatStatus
        rv_riwayat_status.layoutManager = linearLayoutRiwayatStatus
    }

    override fun showDetailRiwayat(idDestinasi: Int, idPemesanan: Int) {
        val intentDetailRiwayat = Intent(context, DetailRiwayatActivity::class.java)
        intentDetailRiwayat.putExtra("ID_DESTINASI", idDestinasi)
        intentDetailRiwayat.putExtra("ID_PEMESANAN", idPemesanan)
        startActivity(intentDetailRiwayat)
    }

    override fun showDataError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showData(
        dataInfo: GetDestinasiResponse.Data?,
        dataPemesananRiwayatResponse: GetPemesananRiwayatResponse.Data,
        holder: AdapterRiwayatStatus.ViewHolder
    ) {
        if (dataInfo != null) {
            if (dataInfo.gambarList.isNotEmpty()) {
                Glide
                    .with(holder.itemView.context)
                    .load(dataInfo.gambarList[0])
                    .centerCrop()
                    .into(holder.itemView.iv_riwayat_item)
            } else {
                Glide
                    .with(holder.itemView.context)
                    .load("https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
                    .centerCrop()
                    .into(holder.itemView.iv_riwayat_item)
            }

            holder.itemView.tv_riwayat_item_title.text = dataInfo.namaTrip

            val berangkatTahun      = extractTahun(dataInfo.berangkat)
            val berangkatBulan      = extractBulan(dataInfo.berangkat)
            val namaBulanBerangkat:String  = changeBulan(berangkatBulan)
            val berangkatTanggal    = extractTanggal(dataInfo.berangkat)

            val pulangTahun         = extractTahun(dataInfo.pulang)
            val pulangBulan         = extractBulan(dataInfo.pulang)
            val namaBulanPulang:String     = changeBulan(pulangBulan)
            val pulangTanggal       = extractTanggal(dataInfo.pulang)

            if (namaBulanBerangkat == namaBulanPulang) {
                holder.itemView.tv_riwayat_item_date.text =
                    "$berangkatTanggal $namaBulanBerangkat - $pulangTanggal $namaBulanPulang $pulangTahun"
            } else {
                holder.itemView.tv_riwayat_item_date.text =
                    "$berangkatTanggal $namaBulanBerangkat $berangkatTahun - $pulangTanggal $namaBulanPulang $pulangTahun"
            }

            val dibuatTahun         = extractTahun(dataPemesananRiwayatResponse.pemesanan.createdAt)
            val dibuatBulan         = extractBulan(dataPemesananRiwayatResponse.pemesanan.createdAt)
            val namaBulanDibuat     = changeBulan(dibuatBulan)
            val dibuatTanggal       = extractTanggal(dataPemesananRiwayatResponse.pemesanan.createdAt)

            holder.itemView.tv_riwayat_item_made_date.text =
                "$dibuatTanggal $namaBulanDibuat $dibuatTahun"

            val df = DecimalFormat("#,###")
            df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
            holder.itemView.tv_riwayat_item_money.text = "Rp. ${df.format(dataPemesananRiwayatResponse.pemesanan.total)}"

            when (dataPemesananRiwayatResponse.pemesanan.status) {
                "disetujui" -> {
                    holder.itemView.tv_riwayat_item_status.text = "Disetujui"
                    holder.itemView.tv_riwayat_item_status.setTextColor(Color.parseColor("#00C12B"))
                    holder.itemView.view_riwayat_item_status.setBackgroundResource(R.drawable.bg_riwayat_item_status_green)
                }
                "ditolak" -> {
                    holder.itemView.tv_riwayat_item_status.text = "Ditolak"
                    holder.itemView.tv_riwayat_item_status.setTextColor(Color.parseColor("#D15050"))
                    holder.itemView.view_riwayat_item_status.setBackgroundResource(R.drawable.bg_riwayat_item_status_red)
                }
                "expired" -> {
                    holder.itemView.tv_riwayat_item_status.text = "Expired"
                    holder.itemView.tv_riwayat_item_status.setTextColor(Color.parseColor("#777777"))
                    holder.itemView.view_riwayat_item_status.setBackgroundResource(R.drawable.bg_riwayat_item_status_grey)
                }
            }
        }
    }

    fun extractTanggal(tanggal: String): String {
        return tanggal.subSequence(8,10).toString()
    }

    fun extractBulan(bulan: String): String {
        return bulan.subSequence(5,7).toString()
    }

    fun extractTahun(tahun: String): String {
        return tahun.subSequence(0,4).toString()
    }

    fun changeBulan(bulan: String): String {
        var namaBulan = ""
        when (bulan) {
            "01" -> namaBulan = "Januari"
            "02" -> namaBulan = "Februari"
            "03" -> namaBulan = "Maret"
            "04" -> namaBulan = "April"
            "05" -> namaBulan = "Mei"
            "06" -> namaBulan = "Juni"
            "07" -> namaBulan = "Juli"
            "08" -> namaBulan = "Agustus"
            "09" -> namaBulan = "September"
            "10" -> namaBulan = "Oktober"
            "11" -> namaBulan = "November"
            "12" -> namaBulan = "Desember"
        }
        return namaBulan
    }

    override fun showLoadingDialog() {
        val fm=fragmentManager
        MyFragment.isCancelable = false
        fm?.let { MyFragment.show(it, "Fragment") }
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }
}