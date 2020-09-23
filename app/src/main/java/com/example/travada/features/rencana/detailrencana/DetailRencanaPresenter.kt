package com.example.travada.features.rencana.detailrencana

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRencanaPresenter(val listener: Listener) : AppCompatActivity() {

    fun getDetailRencana(){
        TPApiClient.TP_API_SERVICES.getDetailDestination().enqueue(object : Callback<GetDestinasiDetailResponse> {
            override fun onFailure(call: Call<GetDestinasiDetailResponse>, t: Throwable) {
                t.message?.let {
            // isi
                }
            }

            override fun onResponse(call: Call<GetDestinasiDetailResponse>, response: Response<GetDestinasiDetailResponse>) {
                response.body()?.data?.let {
                    listener.implementDetailDestinasi(it)
                }
            }
        })
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


        fun showDataRencanaPerjalanan(
            adapterRencanaPerjalanan: RencanaPerjalananAdapter,
            linearLayoutRencanaWisata: LinearLayoutManager) {}


        fun btnSelengkapnyaDeskripsi()

        fun btnSelengkapnyaPerjalanan()

        fun implementDetailDestinasi(getDestinasi: GetDestinasiDetailResponse.Data){}


    }
}