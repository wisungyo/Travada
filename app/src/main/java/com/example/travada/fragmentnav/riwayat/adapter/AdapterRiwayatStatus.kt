package com.example.travada.fragmentnav.riwayat.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.sampeldata.DataRiwayat
import kotlinx.android.synthetic.main.fragment_riwayat_item.view.*

class AdapterRiwayatStatus(val listDataRiwayat: ArrayList<DataRiwayat>):
    RecyclerView.Adapter<AdapterRiwayatStatus.ViewHolder>(){
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_riwayat_item, parent, false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.iv_riwayat_item.setBackgroundResource(listDataRiwayat[position].img)
        holder.itemView.tv_riwayat_item_title.text = listDataRiwayat[position].title
        holder.itemView.tv_riwayat_item_date.text = "${listDataRiwayat[position].startDate} - ${listDataRiwayat[position].endDate}"
        holder.itemView.tv_riwayat_item_made_date.text = listDataRiwayat[position].madeDate
        holder.itemView.tv_riwayat_item_status.text = listDataRiwayat[position].status

        when (listDataRiwayat[position].status) {
            "Disetujui" -> {
                holder.itemView.tv_riwayat_item_status.setTextColor(Color.parseColor("#00C12B"))
                holder.itemView.view_riwayat_item_status.setBackgroundResource(R.drawable.bg_riwayat_item_status_green)
            }
            "Ditolak" -> {
                holder.itemView.tv_riwayat_item_status.setTextColor(Color.parseColor("#D15050"))
                holder.itemView.view_riwayat_item_status.setBackgroundResource(R.drawable.bg_riwayat_item_status_red)
            }
            "Expired" -> {
                holder.itemView.tv_riwayat_item_status.setTextColor(Color.parseColor("#777777"))
                holder.itemView.view_riwayat_item_status.setBackgroundResource(R.drawable.bg_riwayat_item_status_grey)
            }
            else -> {
                holder.itemView.tv_riwayat_item_status.setTextColor(Color.parseColor("#777777"))
                holder.itemView.view_riwayat_item_status.setBackgroundResource(R.drawable.bg_riwayat_item_status_grey)
            }
        }
    }

    override fun getItemCount(): Int {
        return listDataRiwayat.size
    }

}