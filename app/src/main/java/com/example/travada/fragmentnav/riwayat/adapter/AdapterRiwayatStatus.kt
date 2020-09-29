package com.example.travada.fragmentnav.riwayat.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.fragmentnav.riwayat.fragmentriwayat.StatusFragmentPresenter
import com.example.travada.fragmentnav.riwayat.pojo.GetPemesananRiwayatResponse
import kotlinx.android.synthetic.main.fragment_riwayat_item.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class AdapterRiwayatStatus(val listDataRiwayat: List<GetPemesananRiwayatResponse.Data>, val presenter: StatusFragmentPresenter, val listener: StatusFragmentPresenter.ListenerAdapter):
    RecyclerView.Adapter<AdapterRiwayatStatus.ViewHolder>(){
    lateinit var holder: ViewHolder
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_riwayat_item, parent, false
            )
        holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.getDestinasiInfo(listDataRiwayat[position], position, holder)

        holder.itemView.wrapper_riwayat_proses_item.setOnClickListener {
            presenter.goToDetailRiwayat(listDataRiwayat[position].idDestinasi)
        }
    }

    override fun getItemCount(): Int {
        return listDataRiwayat.size
    }

}