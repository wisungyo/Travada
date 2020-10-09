package com.example.travada.fragmentnav.notifikasi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.fragmentnav.notifikasi.NotifikasiFragmentPresenter
import com.example.travada.fragmentnav.notifikasi.model.DataNotifikasi
import kotlinx.android.synthetic.main.list_notifikasi.view.*

class NotifikasiAdapter(
    val listNotifikasi: ArrayList<DataNotifikasi>,
    val presenter: NotifikasiFragmentPresenter
) :
    RecyclerView.Adapter<NotifikasiAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_notifikasi, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNotifikasi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvJudulNotifikasi.setText(listNotifikasi[position].judul)
        holder.itemView.tvPesanNotifikasi.setText(listNotifikasi[position].pesan)
        holder.itemView.tvTanggalNotifikasi.setText(listNotifikasi[position].tanggal)
        holder.itemView.tvKategori.setText(listNotifikasi[position].kategori)

        when (listNotifikasi[position].kategori) {
            "travasave" -> {
                holder.itemView.ivImagePersonTransaksi.setBackgroundResource(R.drawable.ic_notif_tsave)
            }
            "travaplan" -> {
                holder.itemView.ivImagePersonTransaksi.setBackgroundResource(R.drawable.ic_notif_tplan)
            }
        }

        holder.itemView.viewNotifikasi.setOnClickListener {
            presenter.goToDetailNotifikasi(listNotifikasi[position])
            holder.itemView.viewNotifikasi.setBackgroundResource(R.drawable.bg_white_notif)
        }
    }
}