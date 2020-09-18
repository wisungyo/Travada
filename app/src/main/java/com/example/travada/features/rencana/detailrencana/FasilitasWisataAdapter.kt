package com.example.travada.features.rencana.detailrencana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import kotlinx.android.synthetic.main.list_fasilitas_perjalanan.view.*

class FasilitasWisataAdapter(val listFasilitasWisata : ArrayList<DataFasitasWisata>):
    RecyclerView.Adapter<FasilitasWisataAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_fasilitas_perjalanan,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return listFasilitasWisata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvFasilitasRencana.text = listFasilitasWisata[position].deskripsiFasilitas
    }
}