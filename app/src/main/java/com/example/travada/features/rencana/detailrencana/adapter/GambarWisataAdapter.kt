package com.example.travada.features.rencana.detailrencana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.rencana.detailrencana.presenter.DetailRencanaPresenter
import kotlinx.android.synthetic.main.fragment_riwayat_item.view.*
import kotlinx.android.synthetic.main.list_gambar_wisata.view.*

class GambarWisataAdapter( val listGambarWisata: List<String>, val presenter: DetailRencanaPresenter
) : RecyclerView.Adapter<GambarWisataAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_gambar_wisata, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return listGambarWisata.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Glide.with(holder.itemView.context).load(listGambarWisata[position])
//            .into(holder.itemView.ivFotoWisataBanyak)

        if (listGambarWisata.isNotEmpty()) {
            Glide
                .with(holder.itemView.context)
                .load(listGambarWisata[position])
                .centerCrop()
                .into(holder.itemView.ivFotoWisataBanyak)
        } else {
            Glide
                .with(holder.itemView.context)
                .load("https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
                .centerCrop()
                .into(holder.itemView.ivFotoWisataBanyak)
        }

        holder.itemView.ivFotoWisataBanyak.setOnClickListener {
            presenter.gambarDetail(listGambarWisata[position])
        }
    }
}

