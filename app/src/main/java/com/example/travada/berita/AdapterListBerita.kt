package com.example.travada.berita

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.mainpage.DataBerita
import kotlinx.android.synthetic.main.berita_item.view.*

class AdapterListBerita (val listBerita: ArrayList<DataBerita>):
        RecyclerView.Adapter<AdapterListBerita.ViewHolder>() {
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.berita_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.iv_berita_item.setBackgroundResource(listBerita[position].img)
        holder.itemView.tv_berita_item_title.text = listBerita[position].title
        holder.itemView.tv_berita_item_date.text = "Berlaku sampai ${listBerita[position].date}"

        if (position == 0) {
            holder.itemView.cl_berita_item.setPadding(0, 8, 0, 4)
        } else if (position == listBerita.size-1) {
            holder.itemView.cl_berita_item.setPadding(0, 0, 0, 32)
        }
    }

    override fun getItemCount(): Int {
        return listBerita.size
    }
}