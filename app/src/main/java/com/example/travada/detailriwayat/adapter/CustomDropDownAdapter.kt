package com.example.travada.detailriwayat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.travada.R
import com.example.travada.sampeldata.SaldoSpinnerData
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class CustomDropDownAdapter(val context: Context, var dataSource: List<SaldoSpinnerData>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.cicilan_spinner_item, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        vh.jumlah.text = "Rp. ${df.format(dataSource[position].jumlah)}"
        vh.title.text = dataSource[position].title
        vh.source.text = dataSource[position].source

        return view
    }

    override fun getItem(position: Int): Any? {
        return dataSource[position]
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private class ItemHolder(row: View?) {
        val title: TextView = row?.findViewById(R.id.tv_spinner_cicilan_title) as TextView
        val jumlah: TextView = row?.findViewById(R.id.tv_spinner_cicilan_jumlah) as TextView
        val source: TextView = row?.findViewById(R.id.tv_spinner_cicilan_source) as TextView
    }

}