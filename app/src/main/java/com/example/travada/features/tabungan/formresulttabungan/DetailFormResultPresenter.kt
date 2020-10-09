package com.example.travada.features.tabungan.formresulttabungan

import com.example.travada.features.tabungan.adapter.BarengTemanAdapter
import com.example.travada.features.tabungan.models.DataTabungBareng

class DetailFormResultPresenter (val listener: Listener){
    fun fetchTabungBarengData(){
        val listTabungBareng = arrayListOf(
            DataTabungBareng(
                "Nanda Adi",
                "1212131",
                "AN"
            ),
            DataTabungBareng(
                "Abigail",
                "1212122",
                "A"
            ),
            DataTabungBareng(
                "Nicholas",
                "3434343",
                "N"
            )
        )
        val adapterTabungBareng = BarengTemanAdapter(listTabungBareng)
        listener.showDataTabungBareng(adapterTabungBareng)

    }
    interface Listener{
        fun showDataTabungBareng(adapterTabungBareng: BarengTemanAdapter)
    }
}