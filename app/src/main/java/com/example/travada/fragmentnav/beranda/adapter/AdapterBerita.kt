package com.example.travada.fragmentnav.beranda.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.fragmentnav.beranda.BerandaFragmentPresenter
import com.example.travada.sampeldata.DataBerita
import kotlinx.android.synthetic.main.main_page_item_berita.view.*

class AdapterBerita (val listBerita: ArrayList<DataBerita>, val presenter: BerandaFragmentPresenter):
        RecyclerView.Adapter<AdapterBerita.ViewHolder>() {
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.main_page_item_berita, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.iv_mainpage_item_berita.setBackgroundResource(listBerita[position].img)

        if (position == listBerita.size-1) {
            // IDKW, 28 becomes 16dp in the result
            holder.itemView.cl_mainpage_item_berita.setPadding(0, 0, 28, 14)
        }

        holder.itemView.cv_mainpage_item_berita.setOnClickListener {
            presenter.goToDetailBerita(listBerita[position])
        }
    }

    override fun getItemCount(): Int {
        return listBerita.size
    }
}