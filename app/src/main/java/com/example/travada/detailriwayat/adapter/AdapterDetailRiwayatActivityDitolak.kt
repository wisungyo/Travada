package com.example.travada.detailriwayat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.detailriwayat.presenter.DetailRiwayatActivityPresenter
import com.example.travada.features.rencana.pojo.GetPemesananDetailResponse

class AdapterDetailRiwayatActivityDitolak(
    private val data: GetPemesananDetailResponse.Data?,
    val presenter: DetailRiwayatActivityPresenter
) :
    RecyclerView.Adapter<AdapterDetailRiwayatActivityDitolak.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.detail_riwayat_item_dp_cicilan_expired, parent, false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return 1
    }

}