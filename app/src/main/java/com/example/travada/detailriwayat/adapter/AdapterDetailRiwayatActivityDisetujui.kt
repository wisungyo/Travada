package com.example.travada.detailriwayat.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.detailriwayat.presenter.DetailRiwayatActivityPresenter
import com.example.travada.features.rencana.pojo.GetPemesananDetailResponse
import kotlinx.android.synthetic.main.detail_riwayat_item_dp_cicilan.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.math.abs

class AdapterDetailRiwayatActivityDisetujui(
    private val data: List<GetPemesananDetailResponse.Data.Cicilan>,
    val presenter: DetailRiwayatActivityPresenter
) :
        RecyclerView.Adapter<AdapterDetailRiwayatActivityDisetujui.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.detail_riwayat_item_dp_cicilan, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (data.isNotEmpty()) {
            when (position) {
                0 -> {
                    holder.itemView.tv_detail_riwayat_item_title.text = "DP"
                }
                data.size-1 -> {
                    holder.itemView.tv_detail_riwayat_item_title.text = "Pelunasan"
                }
                else -> {
                    holder.itemView.tv_detail_riwayat_item_title.text = "Cicilan"
                }
            }

            val df = DecimalFormat("#,###")
            df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
            holder.itemView.tv_detail_riwayat_item_jumlah.text = "Rp. ${df.format(abs(data[position].jumlah))}"

            holder.itemView.tv_detail_riwayat_item_tempo.text = data[position].jatuhTempo

            when (data[position].status) {
                "expired" -> {
                    holder.itemView.tv_detail_riwayat_item_status.text = "Expired"
                    holder.itemView.tv_detail_riwayat_item_status.setBackgroundResource(
                        R.drawable.bg_detail_riwayat_item_status_grey)
                    holder.itemView.tv_detail_riwayat_item_status.setTextColor(Color.parseColor("#ffffff"))
                    holder.itemView.btn_detail_riwayat_item.isEnabled = false
                    holder.itemView.btn_detail_riwayat_item.setBackgroundResource(R.drawable.bg_detail_riwayat_item_btn_disable)
                }
                "dibayar" -> {
                    holder.itemView.tv_detail_riwayat_item_status.text = "Dibayar"
                    holder.itemView.tv_detail_riwayat_item_status.setBackgroundResource(
                        R.drawable.bg_detail_riwayat_item_status_green)
                    holder.itemView.tv_detail_riwayat_item_status.setTextColor(Color.parseColor("#ffffff"))
                    holder.itemView.btn_detail_riwayat_item.isEnabled = false
                    holder.itemView.btn_detail_riwayat_item.setBackgroundResource(R.drawable.bg_detail_riwayat_item_btn_disable)
                }
                "menunggu pembayaran" -> {
                    holder.itemView.tv_detail_riwayat_item_status.text = "Menunggu Pembayaran"
                    holder.itemView.tv_detail_riwayat_item_status.setBackgroundResource(
                        R.drawable.bg_detail_riwayat_item_status_yellow)
                    holder.itemView.tv_detail_riwayat_item_status.setTextColor(Color.parseColor("#474747"))
                    holder.itemView.btn_detail_riwayat_item.isEnabled = true
                    holder.itemView.btn_detail_riwayat_item.setBackgroundResource(R.drawable.bg_detail_riwayat_item_btn)
                }
                else -> {
                    holder.itemView.tv_detail_riwayat_item_status.text = "Menunggu Persetujuan"
                    holder.itemView.tv_detail_riwayat_item_status.setBackgroundResource(
                        R.drawable.bg_detail_riwayat_item_status_blue)
                    holder.itemView.tv_detail_riwayat_item_status.setTextColor(Color.parseColor("#ffffff"))
                    holder.itemView.btn_detail_riwayat_item.isEnabled = false
                    holder.itemView.btn_detail_riwayat_item.setBackgroundResource(R.drawable.bg_detail_riwayat_item_btn)
                }
            }

            holder.itemView.btn_detail_riwayat_item.setOnClickListener {
//                val intent(this, )
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}