package com.example.travada.features.rencana.detailrencana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import kotlinx.android.synthetic.main.list_rencana_perjalanan.view.*

class RencanaPerjalananAdapter(val listRencanaPerjalanan: List<String>,val presenter: DetailRencanaPresenter) :
    RecyclerView.Adapter<RencanaPerjalananAdapter.ViewHolder>() {


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_rencana_perjalanan,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount():Int {
      return listRencanaPerjalanan.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvHariDetailRencana.text=listRencanaPerjalanan[position]
    }

}