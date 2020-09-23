package com.example.travada.features.rencana.detailrencana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import kotlinx.android.synthetic.main.activity_detail_rencana.view.*
import kotlinx.android.synthetic.main.detail_rencana_parent_layout.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class DetailRencanaAdapter( val listDetailRencana: List<GetDestinasiDetailResponse.Data>,
                            val presenter: DetailRencanaPresenter) :

    RecyclerView.Adapter<DetailRencanaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_rencana_parent_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listDetailRencana.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)

        holder.itemView.tvTitleDetailRencana.text = listDetailRencana[position].namaTrip
        holder.itemView.tvHeadingDetailRencana.text = listDetailRencana[position].namaTrip
        holder.itemView.tvBenua.text = listDetailRencana[position].benua
        holder.itemView.tvWaktu.text = "${listDetailRencana[position].durasi} hari"
        holder.itemView.tvPeserta.text = "${listDetailRencana[position].kapasitas} orang"
        holder.itemView.tvOverviewKonten.text = listDetailRencana[position].overview
        holder.itemView.tvDeskripsiKonten.text = listDetailRencana[position].deskripsi
        holder.itemView.tvBiayaDetailRencana.text = "Rp. ${df.format(listDetailRencana[position].hargaSatuan)}"
        holder.itemView.tvTitleDetailRencana.text = listDetailRencana[position].namaTrip
//
//        val fasilitas = listDetailRencana[position].fasilitas
//        rvFasilitasPerjalan.adapter = (this,R.layout.list_fasilitas_perjalanan,fasilitas)

    }
}