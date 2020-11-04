package com.example.travada.features.rencana.searchpage

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travada.R
import com.example.travada.features.rencana.searchpage.room.ItemSearch
import kotlinx.android.synthetic.main.item_t_p_last_search.view.*

class SearchHistoryAdapter(
    val list: List<ItemSearch>, val presenter: TPSearchSuggestPresenter
) : RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchHistoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_t_p_last_search, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchHistoryAdapter.ViewHolder, position: Int) {
        holder.itemView.tv_query_last_search.text = list[position].name
        holder.itemView.delete_query_history.setOnClickListener {
            presenter.deleteItem(list[position])
        }
        holder.itemView.tv_query_last_search.setOnClickListener {
            presenter.onQuerySearch(list[position].name)
        }
    }


}
