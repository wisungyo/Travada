package com.example.travada.features.rencana.wisnu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.sampeldata.DataCicilan
import kotlinx.android.synthetic.main.rencana_pesan_card_item.view.*

class AdapterPesanRencanaActivity (val listCicilan: ArrayList<DataCicilan>):
    RecyclerView.Adapter<AdapterPesanRencanaActivity.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.rencana_pesan_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.itemView.tv_rencana_pesan_card_item_cicilan.text = listCicilan[position].title
       holder.itemView.tv_rencana_pesan_card_item_bulan.text = listCicilan[position].tempo
       holder.itemView.tv_rencana_pesan_card_item_biaya.text = listCicilan[position].jumlah
    }

    override fun getItemCount(): Int {
        return listCicilan.size
    }


}