package com.example.travada.fragmentnav.notifikasi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.fragmentnav.notifikasi.pojo.GetNotifikasiResponse
import com.example.travada.fragmentnav.notifikasi.presenter.NotifikasiFragmentPresenter
import kotlinx.android.synthetic.main.detail_riwayat_item_dp_cicilan.view.*
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
//        holder.itemView.tvTanggalNotifikasi.setText(listNotifikasi[position].createdAt)
        holder.itemView.tvKategori.setText(listNotifikasi[position].jenis)

        val tahun         = extractTahun(listNotifikasi[position].createdAt)
        val bulan         = extractBulan(listNotifikasi[position].createdAt)
        val namaBulan     = changeBulan(bulan)
        val tanggal       = extractTanggal(listNotifikasi[position].createdAt)

        holder.itemView.tvTanggalNotifikasi.text =
            "$tanggal $namaBulan $tahun"

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



    fun extractTanggal(tanggal: String): String {
        return tanggal.subSequence(8,10).toString()
    }

    fun extractBulan(bulan: String): String {
        return bulan.subSequence(5,7).toString()
    }

    fun extractTahun(tahun: String): String {
        return tahun.subSequence(0,4).toString()
    }

    fun changeBulan(bulan: String): String {
        var namaBulan = ""
        when (bulan) {
            "01" -> namaBulan = "Januari"
            "02" -> namaBulan = "Februari"
            "03" -> namaBulan = "Maret"
            "04" -> namaBulan = "April"
            "05" -> namaBulan = "Mei"
            "06" -> namaBulan = "Juni"
            "07" -> namaBulan = "Juli"
            "08" -> namaBulan = "Agustus"
            "09" -> namaBulan = "September"
            "10" -> namaBulan = "Oktober"
            "11" -> namaBulan = "November"
            "12" -> namaBulan = "Desember"
        }
        return namaBulan
    }



}