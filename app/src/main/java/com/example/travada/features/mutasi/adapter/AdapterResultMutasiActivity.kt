package com.example.travada.features.mutasi.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.features.mutasi.presenter.ResultMutasiActivityPresenter
import com.example.travada.features.rencana.pojo.GetMutasiFilter
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.item_result_mutasi.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class AdapterResultMutasiActivity(
    val listItem: List<GetMutasiFilter.Data>?,
    val presenter: ResultMutasiActivityPresenter
):
    RecyclerView.Adapter<AdapterResultMutasiActivity.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_result_mutasi, parent, false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tahun         = listItem?.get(position)?.createdAt?.let { extractTahun(it) }
        val bulan         = listItem?.get(position)?.createdAt?.let { extractBulan(it) }
        val namaBulan     = bulan?.let { changeBulan(it) }
        val tanggal       = listItem?.get(position)?.createdAt?.let { extractTanggal(it) }

        holder.itemView.tv_item_result_mutasi_bulan.text = "$namaBulan $tahun"

        if (position == 0) {
            holder.itemView.tv_item_result_mutasi_bulan.visibility = View.VISIBLE
            holder.itemView.view_item_result_mutasi.visibility = View.VISIBLE
        } else {
            val currentData = listItem?.get(position)?.createdAt?.let { extractBulan(it) }
            val lastData    = listItem?.get(position-1)?.createdAt?.let { extractBulan(it) }
            if (currentData != lastData) {
                holder.itemView.tv_item_result_mutasi_bulan.visibility = View.VISIBLE
                holder.itemView.view_item_result_mutasi.visibility = View.VISIBLE
            } else {
                holder.itemView.tv_item_result_mutasi_bulan.visibility = View.GONE
                holder.itemView.view_item_result_mutasi.visibility = View.GONE
            }
        }

        val transaction = listItem?.get(position)?.nominal?.get(0)?.toString()
        if (transaction == "-" ) {
            holder.itemView.tv_item_result_mutasi_title.text =
                "${listItem?.get(position)?.namaTujuan} ${listItem?.get(position)?.rekeningTujuan}"

            holder.itemView.tv_item_result_mutasi_source.text = listItem?.get(position)?.namaAsal

            val df = DecimalFormat("#,###")
            df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
            val length = listItem?.get(position)?.nominal?.length
            val nominal = length?.let { listItem?.get(position)?.nominal?.subSequence(1, it) }
            holder.itemView.tv_item_result_mutasi_jumlah.text = "${listItem?.get(position)?.nominal}"
            holder.itemView.tv_item_result_mutasi_jumlah.setTextColor(Color.parseColor("#D15050"))

        } else {
            holder.itemView.tv_item_result_mutasi_title.text =
                "${listItem?.get(position)?.namaAsal} ${listItem?.get(position)?.rekeningAsal}"

            holder.itemView.tv_item_result_mutasi_source.text = listItem?.get(position)?.namaTujuan

            val df = DecimalFormat("#,###")
            df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
            val length = listItem?.get(position)?.nominal?.length
            val nominal = length?.let { listItem?.get(position)?.nominal?.subSequence(1, it) }
            holder.itemView.tv_item_result_mutasi_jumlah.text = "+ ${listItem?.get(position)?.nominal}"
            holder.itemView.tv_item_result_mutasi_jumlah.setTextColor(Color.parseColor("#43AC43"))
        }


        holder.itemView.tv_item_result_mutasi_detail_tanggal.text = "$tanggal $namaBulan $tahun"
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
            "01" -> namaBulan = "Jan"
            "02" -> namaBulan = "Feb"
            "03" -> namaBulan = "Mar"
            "04" -> namaBulan = "Apr"
            "05" -> namaBulan = "Mei"
            "06" -> namaBulan = "Jun"
            "07" -> namaBulan = "Jul"
            "08" -> namaBulan = "Agu"
            "09" -> namaBulan = "Sep"
            "10" -> namaBulan = "Okt"
            "11" -> namaBulan = "Nov"
            "12" -> namaBulan = "Des"
        }
        return namaBulan
    }

    override fun getItemCount(): Int {
        return if (listItem != null) {
            listItem.size
        } else {
            0
        }
    }


}