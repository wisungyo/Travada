package com.example.travada.features.mutasi.presenter

import androidx.appcompat.app.AppCompatActivity

class MutasiActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun goToResultMutasi() {
        listener.showResultMutasi()
    }

    interface Listener {
        fun showResultMutasi()
    }
}