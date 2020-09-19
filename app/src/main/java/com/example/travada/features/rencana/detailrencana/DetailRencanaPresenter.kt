package com.example.travada.features.rencana.detailrencana

import android.net.sip.SipSession
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R

class DetailRencanaPresenter(val listener: Listener) : AppCompatActivity() {


    companion object {
        val INTENT_PARCELABEL = "OBJECT_IMAGE"
    }

    fun fetchGambarWisata() {
        val gambarWisata = arrayListOf(
            DataGambarWisata(R.drawable.img_tokyo),
            DataGambarWisata(R.drawable.img_kyoto),
            DataGambarWisata(R.drawable.img_china),
            DataGambarWisata(R.drawable.img_beijing),
            DataGambarWisata(R.drawable.img_kyoto)
        )
        val adapterGambar = GambarWisataAdapter(gambarWisata)
        val linearLayoutGambarWisata =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        listener.showDataGambarWisata(adapterGambar,linearLayoutGambarWisata)
    }

    fun fetchFasilitasWisata() {
        val fasilitasPerjalan = arrayListOf(
            DataFasitasWisata("Return full servica flight"),
            DataFasitasWisata("3/4 starts hotel"),
            DataFasitasWisata("Air port transfer"),
            DataFasitasWisata("3 days subway pass"),
            DataFasitasWisata("Trip Buddy")
        )
        val adapterFasilitasPerjalanan = FasilitasWisataAdapter(fasilitasPerjalan)
        val linearLayoutFasilitasWisata = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        listener.showDataFasilitasWisata(adapterFasilitasPerjalanan,linearLayoutFasilitasWisata)
    }

    fun fetchRencanaPerjalanan() {
        val rencanaPerjalanan = arrayListOf(
            DataRencanaPerjalanan(
                "Hari ke-1",
                "Jakarta",
                "Peserta akan berkumpul di Bandara Soekarno Hatta Jakarta sesuai waktu dan tempat yang akan ditetapkan nanti. Setelah check-in, mengurus bagasi, dan lain-lain, kita akan terbang menuju negara Jepang. Sesampainya di Haneda, kita akan langsung check in Hotel"
            ),
            DataRencanaPerjalanan(
                "Hari ke-2",
                "Tokyo",
                "Peserta akan berkumpul di Bandara Soekarno Hatta Jakarta sesuai waktu dan tempat yang akan ditetapkan nanti. Setelah check-in, mengurus bagasi, dan lain-lain, kita akan terbang menuju negara Jepang. Sesampainya di Haneda, kita akan langsung check in Hotel"
            ),
            DataRencanaPerjalanan(
                "Hari ke-3",
                "China",
                "Peserta akan berkumpul di Bandara Soekarno Hatta Jakarta sesuai waktu dan tempat yang akan ditetapkan nanti. Setelah check-in, mengurus bagasi, dan lain-lain, kita akan terbang menuju negara Jepang. Sesampainya di Haneda, kita akan langsung check in Hotel"
            )
        )
        val adapterRencanaPerjalanan = RencanaPerjalananAdapter(rencanaPerjalanan)
        val linearLayoutRencanaPerjalanan = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        listener.showDataRencanaPerjalanan(adapterRencanaPerjalanan,linearLayoutRencanaPerjalanan)
    }


    interface Listener {

        fun showDataGambarWisata(
            adapterGambarWisata: GambarWisataAdapter,
            linearLayoutGambarWisata: LinearLayoutManager) {}

        fun showDataRencanaPerjalanan(
            adapterRencanaPerjalanan: RencanaPerjalananAdapter,
            linearLayoutRencanaWisata: LinearLayoutManager) {}

        fun showDataFasilitasWisata(
            adapterFasilitasWIsata: FasilitasWisataAdapter,
            linearLayoutFasilitasWisata: LinearLayoutManager) {}

        fun btnSelengkapnyaSatu()

        fun btnSelengkapnyaDua()

    }
}