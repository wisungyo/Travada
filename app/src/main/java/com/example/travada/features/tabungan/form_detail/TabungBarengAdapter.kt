package com.example.travada.features.tabungan.form_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.tabungan.main_tabungan.ListWisataAdapter
import kotlinx.android.synthetic.main.list_liburan_pilihan.view.*
import kotlinx.android.synthetic.main.list_tabung_bareng.view.*

class TabungBarengAdapter(val listTemanGabung: ArrayList<DataTabungBareng>) :
RecyclerView.Adapter<TabungBarengAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_tabung_bareng, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTemanGabung.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemView.tvNamaTemanGabung.setText(listTemanGabung[position].nama)
        holder.itemView.tvRekeningTemanGabung.setText(listTemanGabung[position].nomorRekening)
        holder.itemView.imageTeman.setText(listTemanGabung[position].gambar)

    }

}