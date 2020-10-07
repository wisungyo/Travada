package com.example.travada.features.mutasi.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.features.mutasi.presenter.ResultMutasiActivityPresenter
import com.example.travada.sampeldata.DataMutasi
import kotlinx.android.synthetic.main.item_result_mutasi.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.ArrayList

class AdapterResultMutasiActivity(
    val listItem: ArrayList<DataMutasi>,
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
        val tahun         = extractTahun(listItem[position].tanggal)
        val bulan         = extractBulan(listItem[position].tanggal)
        val namaBulan     = changeBulan(bulan)
        val tanggal       = extractTanggal(listItem[position].tanggal)

        holder.itemView.tv_item_result_mutasi_bulan.text = "$namaBulan $tahun"

        if (position == 0) {
            holder.itemView.tv_item_result_mutasi_bulan.visibility = View.VISIBLE
            holder.itemView.view_item_result_mutasi.visibility = View.VISIBLE
        } else {
            if (listItem[position].tanggal.subSequence(5,7) != listItem[position - 1].tanggal.subSequence(5,7)) {
                holder.itemView.tv_item_result_mutasi_bulan.visibility = View.VISIBLE
                holder.itemView.view_item_result_mutasi.visibility = View.VISIBLE
            } else {
                holder.itemView.tv_item_result_mutasi_bulan.visibility = View.GONE
                holder.itemView.view_item_result_mutasi.visibility = View.GONE
            }
        }

        if (!listItem[position].nama.contains(" ")) {
            holder.itemView.tv_item_result_mutasi_inisial.text = listItem[position].nama.subSequence(0,1)
        } else {
            val first = listItem[position].nama.split(" ").first()
            val last = listItem[position].nama.split(" ").last()
            holder.itemView.tv_item_result_mutasi_inisial.text = "${first.subSequence(0,1)}${last.subSequence(0,1)}"
        }

        holder.itemView.tv_item_result_mutasi_nama.text = listItem[position].nama

        holder.itemView.tv_item_result_mutasi_rekening.text = listItem[position].rekening

        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        if (listItem[position].jenis == "debet") {
            holder.itemView.tv_item_result_mutasi_jumlah.text = "+ ${df.format(listItem[position].jumlah)}"
            holder.itemView.tv_item_result_mutasi_jumlah.setTextColor(Color.parseColor("#43AC43"))
        } else {
            holder.itemView.tv_item_result_mutasi_jumlah.text = "- ${df.format(listItem[position].jumlah)}"
            holder.itemView.tv_item_result_mutasi_jumlah.setTextColor(Color.parseColor("#D15050"))
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
        return listItem.size
    }


}