package com.example.travada.features.tabungan.main_tabungan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travada.R
import kotlinx.android.synthetic.main.list_liburan_pilihan.view.*

class ListWisataAdapter(val listWisata: ArrayList<DataListWisata>) :
    RecyclerView.Adapter<ListWisataAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_liburan_pilihan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listWisata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (listWisata.size > 0) {
            var images = holder.itemView.imagePerson

            holder.itemView.tvNamaWisata.text = listWisata[position].namaWisata
            holder.itemView.tvBiayaWisata.text = listWisata[position].biaya
            holder.itemView.tvTempo.text = listWisata[position].tempo
            Glide.with(holder.itemView.context).load(listWisata[position].gambar).into(images)
        }


    }


}