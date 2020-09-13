package com.example.travada.features.tabungan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.features.tabungan.models.DataTransaksi
import kotlinx.android.synthetic.main.list_transaksi.view.*

class TransaksiAdapter(val listTransaksi: ArrayList<DataTransaksi>) :
    RecyclerView.Adapter<TransaksiAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_transaksi, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return listTransaksi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvImagePersonTransaksi.setText(listTransaksi[position].image)
        holder.itemView.tvKegiatanTansaksi.setText(listTransaksi[position].kegiatan)
        holder.itemView.tvTanggalTransaksi.setText(listTransaksi[position].tanggalTransaksi)
        holder.itemView.tvNominalTransaksi.setText(listTransaksi[position].nominalTransaksi)
        holder.itemView.tvJenisTransaksi.setText(listTransaksi[position].jenisTransaksi)
    }

}