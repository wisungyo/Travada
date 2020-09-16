package com.example.travada.detailriwayat

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.sampeldata.DataCicilan
import kotlinx.android.synthetic.main.berita_item.view.*
import kotlinx.android.synthetic.main.detail_riwayat_item_dp_cicilan.view.*

class AdapterDetailRiwayatActivity (val listItem: ArrayList<DataCicilan>, val presenter: DetailRiwayatActivityPresenter):
    RecyclerView.Adapter<AdapterDetailRiwayatActivity.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.detail_riwayat_item_dp_cicilan_expired, parent, false)

        if (listItem.size > 0) {
            view = LayoutInflater.from(parent.context).inflate(
                    R.layout.detail_riwayat_item_dp_cicilan, parent, false)
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listItem.size > 0) {
            holder.itemView.tv_detail_riwayat_item_title.text = listItem[position].title
            holder.itemView.tv_detail_riwayat_item_jumlah.text = listItem[position].jumlah.toString()
            holder.itemView.tv_detail_riwayat_item_tempo.text = listItem[position].tempo
            holder.itemView.tv_detail_riwayat_item_status.text = listItem[position].status

            when (listItem[position].status) {
                "Expired" -> {
                    holder.itemView.tv_detail_riwayat_item_status.setBackgroundResource(
                        R.drawable.bg_detail_riwayat_item_status_grey)
                    holder.itemView.tv_detail_riwayat_item_status.setTextColor(Color.parseColor("#ffffff"))
                    holder.itemView.btn_detail_riwayat_item.isEnabled = false
                    holder.itemView.btn_detail_riwayat_item.setBackgroundResource(R.drawable.bg_detail_riwayat_item_btn_disable)
                }
                "Dibayar" -> {
                    holder.itemView.tv_detail_riwayat_item_status.setBackgroundResource(
                        R.drawable.bg_detail_riwayat_item_status_green)
                    holder.itemView.tv_detail_riwayat_item_status.setTextColor(Color.parseColor("#ffffff"))
                    holder.itemView.btn_detail_riwayat_item.isEnabled = false
                    holder.itemView.btn_detail_riwayat_item.setBackgroundResource(R.drawable.bg_detail_riwayat_item_btn_disable)
                }
                "Menunggu Persetujuan" -> {
                    holder.itemView.tv_detail_riwayat_item_status.setBackgroundResource(
                        R.drawable.bg_detail_riwayat_item_status_blue)
                    holder.itemView.tv_detail_riwayat_item_status.setTextColor(Color.parseColor("#ffffff"))
                    holder.itemView.btn_detail_riwayat_item.isEnabled = false
                    holder.itemView.btn_detail_riwayat_item.setBackgroundResource(R.drawable.bg_detail_riwayat_item_btn_disable)
                }
            }

            holder.itemView.btn_detail_riwayat_item.setOnClickListener {
//                val intent(this, )
            }
        }
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}