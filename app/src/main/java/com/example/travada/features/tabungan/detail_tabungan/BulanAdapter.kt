package com.example.travada.features.tabungan.detail_tabungan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import kotlinx.android.synthetic.main.list_bulan_transaksi.view.*

class BulanAdapter(val listBulanTransaksi: ArrayList<DataBulan>) :
    RecyclerView.Adapter<BulanAdapter.ViewHolder>() {

    private val viewPoll = RecyclerView.RecycledViewPool()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val tvTitle: TextView
        internal val rvBulanTransaksi: RecyclerView

        init {
            tvTitle = itemView.findViewById(R.id.tvBulan)
            rvBulanTransaksi = itemView.findViewById(R.id.rvBulanTransaksi)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_bulan_transaksi, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listBulanTransaksi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = listBulanTransaksi[position]
        holder.itemView.tvBulan.setText(listBulanTransaksi[position].bulan)

        val layoutManager = LinearLayoutManager(
            holder.rvBulanTransaksi.context, LinearLayoutManager.VERTICAL, false)

        layoutManager.initialPrefetchItemCount = item.listTransfer.size

        // create sub item view adapter
        val subItemAdapter = TransaksiAdapter(item.listTransfer)

        holder.rvBulanTransaksi.layoutManager = layoutManager
        holder.rvBulanTransaksi.adapter = subItemAdapter
        holder.rvBulanTransaksi.setRecycledViewPool(viewPoll)


    }

}