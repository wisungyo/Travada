package com.example.travada.mainpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import kotlinx.android.synthetic.main.main_page_item_tabungan.view.*
import kotlinx.android.synthetic.main.main_page_item_trip.view.*

class AdapterTrip (val listTrip: ArrayList<DataTrip>):
        RecyclerView.Adapter<AdapterTrip.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.main_page_item_trip, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.iv_mainpage_item_trip.setBackgroundResource(listTrip[position].img)
        holder.itemView.tv_mainpage_item_trip_title.text = listTrip[position].title
        holder.itemView.tv_mainpage_item_trip_money.text = listTrip[position].price
        holder.itemView.tv_mainpage_item_trip_day.text = listTrip[position].day

        if (position == listTrip.size-1) {
            // IDKW, 28 becomes 16dp in the result
            holder.itemView.cl_mainpage_item_trip.setPadding(0, 0, 28, 14)
        }
    }

    override fun getItemCount(): Int {
        return listTrip.size
    }


}