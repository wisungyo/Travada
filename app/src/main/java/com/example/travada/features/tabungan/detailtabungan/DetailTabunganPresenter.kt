package com.example.travada.features.tabungan.detailtabungan

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.features.tabungan.adapter.DetailTabunganAdapter
import com.example.travada.features.tabungan.models.DataDetailTabungan

class DetailTabunganPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchData(){
        val detailList = arrayListOf(
            DataDetailTabungan(
                "Enno bening",
                "12000",
                "73647364734",
                "EB"
            ),
            DataDetailTabungan(
                "Enno bening",
                "12000",
                "73647364734",
                "EB"
            ),
            DataDetailTabungan(
                "Enno bening",
                "12000",
                "73647364734",
                "EB"
            ),
            DataDetailTabungan(
                "Enno bening",
                "12000",
                "73647364734",
                "EB"
            )
        )

        val adapterDetailTabungan = DetailTabunganAdapter(detailList)
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listener.showData(adapterDetailTabungan, linearLayout)

    }

    interface Listener {
        fun showData(
            adapterDetailTabungan: DetailTabunganAdapter,
            linearLayoutDetailTabungan: LinearLayoutManager
        )
    }
}