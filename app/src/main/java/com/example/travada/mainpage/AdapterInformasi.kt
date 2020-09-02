package com.example.travada.mainpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import kotlinx.android.synthetic.main.main_page_item_informasi.view.*

class AdapterInformasi (val listInformmasi: ArrayList<DataInformasi>):
    RecyclerView.Adapter<AdapterInformasi.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.main_page_item_informasi, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.iv_mainpage_item_informasi.setBackgroundResource(listInformmasi[position].img)
    }

    override fun getItemCount(): Int {
        return listInformmasi.size
    }

}