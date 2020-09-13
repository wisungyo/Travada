package com.example.travada.features.tabungan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.features.tabungan.detailtabungan.DataDetailTabungan
import kotlinx.android.synthetic.main.list_teman_gabung.view.*

class DetailTabunganAdapter(val listDetail: ArrayList<DataDetailTabungan>) :
    RecyclerView.Adapter<DetailTabunganAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_teman_gabung, parent, false)
        return ViewHolder(
            view
        )
    }


    override fun getItemCount(): Int {
        return listDetail.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvDetailImageTeman.setText(listDetail[position].gambar)
        holder.itemView.tvDetailNamaTeman.setText(listDetail[position].nama)
        holder.itemView.tvDetailNominal.setText(listDetail[position].jumlah)
        holder.itemView.tvDetailRekening.setText(listDetail[position].nomorRekening)
    }
}