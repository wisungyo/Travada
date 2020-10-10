package com.example.travada.features.rencana.wisnu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetCicilanResponse
import com.example.travada.features.rencana.wisnu.presenter.PesanRencanaActivityPresenter
import com.example.travada.sampeldata.DataCicilan
import kotlinx.android.synthetic.main.activity_detail_riwayat.*
import kotlinx.android.synthetic.main.rencana_pesan_card_item.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class AdapterPesanRencanaActivity (val listCicilan: List<GetCicilanResponse.Data>, val presenter: PesanRencanaActivityPresenter):
    RecyclerView.Adapter<AdapterPesanRencanaActivity.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.rencana_pesan_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.itemView.tv_rencana_pesan_card_item_cicilan.text = "DP"
            }
            listCicilan.size-1 -> {
                holder.itemView.tv_rencana_pesan_card_item_cicilan.text = "Pelunasan"
            }
            else -> {
                holder.itemView.tv_rencana_pesan_card_item_cicilan.text = "Cicilan"
            }
        }

        val tahun         = extractTahun(listCicilan[position].jatuhTempo)
        val bulan         = extractBulan(listCicilan[position].jatuhTempo)
        val namaBulan     = changeBulan(bulan)
        val tanggal       = extractTanggal(listCicilan[position].jatuhTempo)

        holder.itemView.tv_rencana_pesan_card_item_bulan.text =
            "$tanggal $namaBulan $tahun"

        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        holder.itemView.tv_rencana_pesan_card_item_biaya.text = "Rp. ${df.format(abs(listCicilan[position].jumlah))}"

//        presenter.addBiaya(abs(listCicilan[position].jumlah))
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

    override fun getItemCount(): Int {
        return listCicilan.size
    }


}