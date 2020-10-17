package com.example.travada.detailriwayat.adapter

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.detailriwayat.presenter.BayarCicilanActivityPresenter
import com.example.travada.sampeldata.DataSpinnerCicilan
import kotlinx.android.synthetic.main.cicilan_spinner_item.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.ArrayList

class AdapterSpinnerBayarCicilan(
    private val listSpinner: ArrayList<DataSpinnerCicilan>,
    val presenter: BayarCicilanActivityPresenter
) : RecyclerView.Adapter<AdapterSpinnerBayarCicilan.ViewHolder>(), SpinnerAdapter {
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.cicilan_spinner_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_spinner_cicilan_title.text = listSpinner[position].title

        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        holder.itemView.tv_spinner_cicilan_jumlah.text = "Rp. ${df.format(listSpinner[position].jumlah)}"

        holder.itemView.tv_spinner_cicilan_source.text = listSpinner[position].source
    }

    override fun getItemCount(): Int {
        return listSpinner.size
    }

    override fun registerDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDropDownView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }
}