package com.example.travada.mainpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import kotlinx.android.synthetic.main.main_page_item_tabungan.view.*

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
    }

    override fun getItemCount(): Int {
        return listTabungan.size
    }


}