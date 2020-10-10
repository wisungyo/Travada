package com.example.travada.detailriwayat.presenter

class BayarCicilanActivityPresenter (val listener: Listener) {

    fun doKonfirmasi() {
        listener.showDialogKonfirmasi(
            "Konfirmasi Pembayaran",
            "Tagihan akan diambil dari saldo kamu"
        )
    }

    interface Listener {
        fun showDialogKonfirmasi(title: String, subtitle: String)
    }
}