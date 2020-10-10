package com.example.travada.detailriwayat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.travada.R
import com.example.travada.sampeldata.SaldoSpinnerData

class CustomDropDownAdapter(val context: Context, var dataSource: List<SaldoSpinnerData>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.custon_spinner_item, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = "Rp. ${dataSource[position].name}"
        vh.img.text = dataSource[position].url

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
        val label: TextView = row?.findViewById(R.id.text) as TextView
        val img: TextView = row?.findViewById(R.id.img) as TextView
    }

}