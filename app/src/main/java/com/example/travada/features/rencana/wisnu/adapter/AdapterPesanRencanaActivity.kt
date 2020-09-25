package com.example.travada.features.rencana.wisnu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetCicilanResponse
import com.example.travada.features.rencana.wisnu.presenter.PesanRencanaActivityPresenter
import com.example.travada.sampeldata.DataCicilan
import kotlinx.android.synthetic.main.rencana_pesan_card_item.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class AdapterPesanRencanaActivity (val listCicilan: List<GetCicilanResponse.Data>, val presenter: PesanRencanaActivityPresenter):
    RecyclerView.Adapter<AdapterPesanRencanaActivity.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.rencana_pesan_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.itemView.tv_rencana_pesan_card_item_cicilan.text = "DP"
            }
            listCicilan.size-1 -> {
                holder.itemView.tv_rencana_pesan_card_item_cicilan.text = "Pelunasan"
            }
            else -> {
                holder.itemView.tv_rencana_pesan_card_item_cicilan.text = "Cicilan"
            }
        }

        holder.itemView.tv_rencana_pesan_card_item_bulan.text = listCicilan[position].jatuhTempo

        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        holder.itemView.tv_rencana_pesan_card_item_biaya.text = "Rp. ${df.format(abs(listCicilan[position].jumlah))}"

//        presenter.addBiaya(abs(listCicilan[position].jumlah))
    }

    override fun getItemCount(): Int {
        return listCicilan.size
    }


}