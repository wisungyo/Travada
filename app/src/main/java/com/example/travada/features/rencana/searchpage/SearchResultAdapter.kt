package com.example.travada.features.rencana.searchpage

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.travada.R
import com.example.travada.features.rencana.pojo.GetDestinasiAllResponse
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_t_p_search_page.view.*
import java.util.*

class SearchResultAdapter(val list:List<GetDestinasiAllResponse.Data>, val presenter: TPSearchResultPresenter) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_t_p_search_page, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        if (list[position].gambarList.isNotEmpty()){
            Glide
                .with(holder.itemView.context)
                .load(list[position].gambarList[0])

                .apply(
                    RequestOptions.bitmapTransform(
                        RoundedCornersTransformation(
                            38,
                            0,
                            RoundedCornersTransformation.CornerType.ALL
                        )
                    )
                )
                .into(holder.itemView.iv_image)
        }

        holder.itemView.tv_place.text = list[position].benua
        holder.itemView.tv_title.text = list[position].namaTrip
        holder.itemView.tv_duration.text = "${list[position].durasi} hari"
        holder.itemView.tv_person.text = "${list[position].kapasitas} orang"
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        holder.itemView.tv_price.text = "${numberFormat.format(list[position].hargaSatuan)}"

        // adding click-listener
        holder.itemView.SearchResult_Item.setOnClickListener {
            presenter.showClickedItem(list[position].id)
        }
    }


}