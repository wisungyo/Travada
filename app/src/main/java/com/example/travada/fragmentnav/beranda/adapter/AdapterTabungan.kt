package com.example.travada.fragmentnav.beranda.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetTabunganUserAll
import com.example.travada.fragmentnav.beranda.BerandaFragmentPresenter
import kotlinx.android.synthetic.main.main_page_item_tabungan.view.*

class AdapterTabungan(val listTabungan: List<GetTabunganUserAll.Data>, val presenter: BerandaFragmentPresenter):
    RecyclerView.Adapter<AdapterTabungan.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.main_page_item_tabungan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemView.iv_mainpage_item_tabungan.setBackgroundResource(listTabungan[position].gambarTabungan)

        Glide
            .with(holder.itemView.context)
            .load(listTabungan[position].gambarTabungan)
            .circleCrop()
            .into(holder.itemView.iv_mainpage_item_tabungan)

        holder.itemView.tv_mainpage_item_tabungan.text = listTabungan[position].tujuan

        val setoran : Double = listTabungan[position].jumlahSetoran.toDouble()
        val tabungan : Double = listTabungan[position].jumlahTabungan.toDouble()

        val progress =  setoran * 100 / tabungan
        Log.d("TABUNGAN", "setoran=$setoran")
        Log.d("TABUNGAN", "tabungan=$tabungan")
        Log.d("TABUNGAN", "progress=$progress")
        holder.itemView.pg_mainpage_item_tebungan.progress = progress.toInt()
        holder.itemView.tv_mainpage_item_tabungan_pg_indikator.text = "${progress.toInt()}%"

        if (position == listTabungan.size-1) {
            // IDKW, 28 becomes 16dp in the result
            holder.itemView.cl_mainpage_item_tabungan.setPadding(0, 0, 28, 14)
        }

        holder.itemView.cv_mainpage_item_tabungan.setOnClickListener {
            presenter.tabunganItemClicked()
        }
    }

    override fun getItemCount(): Int {
        return listTabungan.size
    }


}