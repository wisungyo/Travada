package com.example.travada.features.mutasi.presenter

class PDFMutasiActivityPresenter (val listener: Listener) {

    fun setPDFLayout() {
        listener.showPDF()
//        listener.finishActivity()
    }

    interface Listener {
        fun finishActivity()
        fun showPDF()
    }
}