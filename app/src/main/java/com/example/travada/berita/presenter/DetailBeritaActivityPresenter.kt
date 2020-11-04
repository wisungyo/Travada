package com.example.travada.berita.presenter

class DetailBeritaActivityPresenter(val listener: Listener) {

    fun fetchData() {
        listener.showData()
    }

    interface Listener {
        fun showData()
    }
}