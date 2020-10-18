package com.example.travada.features.tabungan.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.tabungan.maintabungan.TabunganPresenter
import com.example.travada.features.tabungan.pojo.GetTabunganAll
import com.example.travada.features.tabungan.pojo.GetTabunganUserAll
import kotlinx.android.synthetic.main.list_liburan_pilihan.view.*
import kotlinx.android.synthetic.main.main_page_item_tabungan.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.math.abs

class ListTabunganAdapter(
    val listTabungan: List<GetTabunganUserAll.Data>,
    val presenter: TabunganPresenter
) : RecyclerView.Adapter<ListTabunganAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_liburan_pilihan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTabungan.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)

        var tempo = "Rp.${df.format(abs(listTabungan[position].jumlahSetoran))} dari Rp.${df.format(
            abs(listTabungan[position].jumlahTabungan)
        )}"

        holder.itemView.tvNamaWisata.text = listTabungan[position].tujuan
        holder.itemView.tvBiayaWisata.text = tempo
        // holder.itemView.tvTempoWisata.text = "${listWisata[position].tempo} bulan lagi"

        Glide
            .with(holder.itemView.context)
            .load( listTabungan[position].gambarTabungan)
            .circleCrop()
            .into(holder.itemView.ivFotoWisata)


        val setoran : Double = listTabungan[position].jumlahSetoran.toDouble()
        val tabungan : Double = listTabungan[position].jumlahTabungan.toDouble()

        val progress =  setoran * 100 / tabungan
        Log.d("TABUNGAN", "setoran=$setoran")
        Log.d("TABUNGAN", "tabungan=$tabungan")
        Log.d("TABUNGAN", "progress=$progress")
        holder.itemView.pbWisata.progress = progress.toInt()
        holder.itemView.tvProgresNumber.text = "${progress.toInt()} %"

        holder.itemView.clTabuganList.setOnClickListener {
            presenter.itemClicked(listTabungan[position].id)
        }
    }
}