package com.example.travada.features.tabungan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.features.tabungan.models.DataTabungBareng
import kotlinx.android.synthetic.main.list_tabung_bareng_teman.view.*

class BarengTemanAdapter(val listBarengTeman: ArrayList<DataTabungBareng>) :
    RecyclerView.Adapter<BarengTemanAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_tabung_bareng_teman, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return listBarengTeman.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.tvNamaGabung.setText(listBarengTeman[position].nama)
        holder.itemView.tvRekeningGabung.setText(listBarengTeman[position].nomorRekening)
        holder.itemView.tvImageTeman.setText(listBarengTeman[position].inisial)

    }
}