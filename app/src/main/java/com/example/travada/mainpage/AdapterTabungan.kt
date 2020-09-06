package com.example.travada.mainpage

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import kotlinx.android.synthetic.main.main_page_item_tabungan.view.*
import kotlinx.android.synthetic.main.main_page_item_trip.view.*

class AdapterTabungan (val listTabungan: ArrayList<DataTabungan>):
    RecyclerView.Adapter<AdapterTabungan.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.main_page_item_tabungan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.iv_mainpage_item_tabungan.setBackgroundResource(listTabungan[position].img)
        holder.itemView.tv_mainpage_item_tabungan.text = listTabungan[position].title
        holder.itemView.pg_mainpage_item_tebungan.progress = listTabungan[position].progress
        holder.itemView.tv_mainpage_item_tabungan_pg_indikator.text = "${listTabungan[position].progress}%"

        if (position == listTabungan.size-1) {
            // IDKW, 28 becomes 16dp in the result
            holder.itemView.cl_mainpage_item_tabungan.setPadding(0, 0, 28, 14)
        }
    }

    override fun getItemCount(): Int {
        return listTabungan.size
    }


}