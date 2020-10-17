package com.example.travada.features.tabungan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.tabungan.maintabungan.TabunganPresenter
import com.example.travada.features.tabungan.pojo.GetAllTabunganResponse
import kotlinx.android.synthetic.main.list_liburan_pilihan.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.math.abs

class ListTabunganAdapter(
    val listTabungan: List<GetAllTabunganResponse.Data>,
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
            abs(listTabungan[position].jumlahTabungan))}"

        holder.itemView.tvNamaWisata.text = listTabungan[position].tujuan
        holder.itemView.tvBiayaWisata.text = tempo
        // holder.itemView.tvTempoWisata.text = "${listWisata[position].tempo} bulan lagi"

        if (listTabungan[position].gambarTabungan.isNotEmpty()) {
            Glide
                .with(holder.itemView.context)
                .load( listTabungan[position].gambarTabungan)
                .circleCrop()
                .into(holder.itemView.ivFotoWisata)
        } else {
            Glide
                .with(holder.itemView.context)
                .load( "https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
                .circleCrop()
                .into(holder.itemView.ivFotoWisata)
        }

        holder.itemView.clTabuganList.setOnClickListener {
            presenter.itemClicked(listTabungan[position].id)
        }

    }
}