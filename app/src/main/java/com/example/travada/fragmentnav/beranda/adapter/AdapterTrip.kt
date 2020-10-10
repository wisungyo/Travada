package com.example.travada.fragmentnav.beranda.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetPopulerResponse
import com.example.travada.fragmentnav.beranda.BerandaFragmentPresenter
import kotlinx.android.synthetic.main.main_page_item_trip.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class AdapterTrip(val listTrip: List<GetPopulerResponse.Data>, val presenter: BerandaFragmentPresenter):
        RecyclerView.Adapter<AdapterTrip.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.main_page_item_trip, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listTrip[position].gambarList.isNotEmpty()) {
            Glide
                .with(holder.itemView.context)
                .load( listTrip[position].gambarList[0])
                .centerCrop()
                .into(holder.itemView.iv_mainpage_item_trip)
        } else {
            Glide
                .with(holder.itemView.context)
                .load( "https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
                .centerCrop()
                .into(holder.itemView.iv_mainpage_item_trip)
        }

        holder.itemView.tv_mainpage_item_trip_title.text = listTrip[position].namaTrip

        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        holder.itemView.tv_mainpage_item_trip_money.text = "Rp. ${df.format(listTrip[position].hargaSatuan)}"

        holder.itemView.tv_mainpage_item_trip_day.text = "${listTrip[position].durasi} hari"

        if (position == listTrip.size-1) {
            // IDKW, 28 becomes 16dp in the result
            holder.itemView.cl_mainpage_item_trip.setPadding(0, 0, 28, 14)
        }

        holder.itemView.cv_mainpage_item_trip.setOnClickListener {
            presenter.tripItemClicked(listTrip[position].id)
        }
    }

    override fun getItemCount(): Int {
        return listTrip.size
    }


}