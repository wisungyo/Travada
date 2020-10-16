package com.example.travada.fragmentnav.notifikasi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.fragmentnav.notifikasi.pojo.GetNotifikasiResponse
import com.example.travada.fragmentnav.notifikasi.presenter.NotifikasiFragmentPresenter
import kotlinx.android.synthetic.main.list_notifikasi.view.*

class NotifikasiAdapter(
    val listNotifikasi: List<GetNotifikasiResponse.Data>,
    val presenter: NotifikasiFragmentPresenter
) : RecyclerView.Adapter<NotifikasiAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_notifikasi, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNotifikasi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvJudulNotifikasi.setText(listNotifikasi[position].judul)
        holder.itemView.tvPesanNotifikasi.setText(listNotifikasi[position].pesan)
        holder.itemView.tvTanggalNotifikasi.setText(listNotifikasi[position].createdAt)
        holder.itemView.tvKategori.setText(listNotifikasi[position].jenis)

        when (listNotifikasi[position].jenis) {
            "Travasave" -> {
                holder.itemView.ivImagePersonTransaksi.setBackgroundResource(R.drawable.ic_notif_tsave)
            }
            "Travaplan" -> {
                holder.itemView.ivImagePersonTransaksi.setBackgroundResource(R.drawable.ic_notif_tplan)
            }
        }

        when(listNotifikasi[position].terbaca){
            true -> {
                holder.itemView.viewNotifikasi.setBackgroundResource(R.drawable.bg_white_notif)
            }
        }

        holder.itemView.viewNotifikasi.setOnClickListener {
            val idNotif = listNotifikasi[position].id
            presenter.goToDetailNotifikasi(idNotif)
            Log.d("checkerr","${idNotif}")
            // holder.itemView.viewNotifikasi.setBackgroundResource(R.drawable.bg_white_notif)
        }
    }
}