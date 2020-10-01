package com.example.travada.features.tabungan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.tabungan.models.DataWisata
import kotlinx.android.synthetic.main.list_liburan_pilihan.view.*

class ListWisataAdapter(val listWisata: ArrayList<DataWisata>) :
    RecyclerView.Adapter<ListWisataAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_liburan_pilihan, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return listWisata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var images = holder.itemView.ivFotoWisata
        holder.itemView.tvNamaWisata.text = listWisata[position].namaWisata
        holder.itemView.tvBiayaWisata.text = "Rp.${listWisata[position].biaya}"
        holder.itemView.tvTempoWisata.text = "${listWisata[position].tempo} bulan lagi"
        Glide.with(holder.itemView.context).load(listWisata[position].gambar).into(images)
    }
}