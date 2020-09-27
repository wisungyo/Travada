package com.example.travada.features.rencana.searchpage

class TPFilterPagePresenter(val listener:Listener) {



    interface Listener {
        fun onValueChange(minValue: Number, maxValue: Number)
        fun onFilter()
    }
}