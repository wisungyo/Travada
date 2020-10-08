package com.example.travada.fragmentnav.notifikasi

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.fragmentnav.notifikasi.adapter.NotifikasiAdapter
import com.example.travada.fragmentnav.notifikasi.model.DataNotifikasi

class NotifikasiFragmentPresenter(val listener: Listener) {

    fun fetchDataNotifikasi(){
         val notifikasi = arrayListOf(
            DataNotifikasi(
                "Persetujuan",
                "Hanif telah menerima anda sebagai member dari Trava Save selamat ya kamu jadi yang terbaik ",
                "travasave",
                "12-12-2020 , 09:18"
            ),
            DataNotifikasi(
                "Persetujuan",
                "Hanif telah menerima anda sebagai member dari Trava Save",
                "travaplan",
                "12-12-2020 , 09:18"
            ),
            DataNotifikasi(
                "Persetujuan",
                "Hanif telah menerima anda sebagai member dari Trava Save",
                "travaplan",
                "12-12-2020 , 09:18"
            ),
            DataNotifikasi(
                "Persetujuan",
                "Hanif telah menerima anda sebagai member dari Trava Save",
                "travaplan",
                "12-12-2020 , 09:18"
            ),
            DataNotifikasi(
                "Persetujuan",
                "Hanif telah menerima anda sebagai member dari Trava Save",
                "travaplan",
                "12-12-2020 , 09:18"
            ),
            DataNotifikasi(
                "Persetujuan",
                "Hanif telah menerima anda sebagai member dari Trava Save",
                "travasave",
                "12-12-2020 , 09:18"
            )
        )

        val adapterNotifikasi = NotifikasiAdapter(notifikasi)
        listener.showData(adapterNotifikasi)
    }

    interface Listener {
        fun showData(notifikasiAdapter: NotifikasiAdapter)

        fun showDetaiNotifikasi (dataNotifikasi : DataNotifikasi)
    }
}