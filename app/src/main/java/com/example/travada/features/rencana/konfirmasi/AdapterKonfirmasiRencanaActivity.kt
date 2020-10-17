package com.example.travada.features.rencana.konfirmasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.sampeldata.DataCicilanUser
import kotlinx.android.synthetic.main.konfirmasi_rencana_item.view.*
import kotlin.collections.ArrayList


class AdapterKonfirmasiRencanaActivity (val listCicilanUser: ArrayList<DataCicilanUser>, val presenter: KonfirmasiRencanaActivityPresenter):
    RecyclerView.Adapter<AdapterKonfirmasiRencanaActivity.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.konfirmasi_rencana_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (!listCicilanUser[position].status) {
            holder.itemView.iv_konfirmasi_rencana_item_check.visibility = View.GONE
            holder.itemView.iv_konfirmasi_rencana_item_arrow.visibility = View.VISIBLE
            holder.itemView.iv_konfirmasi_rencana_item_img.visibility = View.VISIBLE
            holder.itemView.tv_konfirmasi_rencana_item_name.text = "Orang ${position+1}"
            holder.itemView.tv_konfirmasi_rencana_item_name.visibility = View.VISIBLE
            holder.itemView.tv_konfirmasi_rencana_item_name_checked.text = listCicilanUser[position].name
            holder.itemView.tv_konfirmasi_rencana_item_name_checked.visibility = View.GONE
            holder.itemView.tv_konfirmasi_rencana_item_nomor_note.visibility = View.GONE
            holder.itemView.tv_konfirmasi_rencana_item_nomor.text = listCicilanUser[position].phone
            holder.itemView.tv_konfirmasi_rencana_item_nomor.visibility = View.GONE
            holder.itemView.tv_konfirmasi_rencana_item_email_note.visibility = View.GONE
            holder.itemView.tv_konfirmasi_rencana_item_email.text = listCicilanUser[position].email
            holder.itemView.tv_konfirmasi_rencana_item_email.visibility = View.GONE
        } else {
            holder.itemView.iv_konfirmasi_rencana_item_check.visibility = View.VISIBLE
            holder.itemView.iv_konfirmasi_rencana_item_arrow.visibility = View.GONE
            holder.itemView.iv_konfirmasi_rencana_item_img.visibility = View.GONE
            holder.itemView.tv_konfirmasi_rencana_item_name.text = "Orang ${position+1}"
            holder.itemView.tv_konfirmasi_rencana_item_name.visibility = View.GONE
            holder.itemView.tv_konfirmasi_rencana_item_name_checked.text = listCicilanUser[position].name
            holder.itemView.tv_konfirmasi_rencana_item_name_checked.visibility = View.VISIBLE
            holder.itemView.tv_konfirmasi_rencana_item_nomor_note.visibility = View.VISIBLE
            holder.itemView.tv_konfirmasi_rencana_item_nomor.text = listCicilanUser[position].phone
            holder.itemView.tv_konfirmasi_rencana_item_nomor.visibility = View.VISIBLE
            holder.itemView.tv_konfirmasi_rencana_item_email_note.visibility = View.VISIBLE
            holder.itemView.tv_konfirmasi_rencana_item_email.text = listCicilanUser[position].email
            holder.itemView.tv_konfirmasi_rencana_item_email.visibility = View.VISIBLE
        }

        holder.itemView.cv_konfirmasi_rencana_item.setOnClickListener {
            presenter.doDetailOrang(position)
        }
    }

    override fun getItemCount(): Int {
        return listCicilanUser.size
    }
}