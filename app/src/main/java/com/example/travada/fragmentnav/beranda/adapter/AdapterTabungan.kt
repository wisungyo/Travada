package com.example.travada.fragmentnav.beranda.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.fragmentnav.beranda.BerandaFragmentPresenter
import com.example.travada.sampeldata.DataTabungan
import kotlinx.android.synthetic.main.main_page_item_tabungan.view.*

class AdapterTabungan (val listTabungan: ArrayList<DataTabungan>, val presenter: BerandaFragmentPresenter):
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

        holder.itemView.cv_mainpage_item_tabungan.setOnClickListener {
            presenter.tabunganItemClicked()
        }
    }

    override fun getItemCount(): Int {
        return listTabungan.size
    }


}