package com.example.travada.features.rencana.detailrencana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.features.rencana.detailrencana.presenter.DetailRencanaPresenter
import kotlinx.android.synthetic.main.list_fasilitas_perjalanan.view.*

class FasilitasWisataAdapter (val listFasilitas: List<String>,val presenter: DetailRencanaPresenter):
    RecyclerView.Adapter<FasilitasWisataAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_fasilitas_perjalanan, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return listFasilitas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvFasilitasRencana.text = listFasilitas[position]
    }
}