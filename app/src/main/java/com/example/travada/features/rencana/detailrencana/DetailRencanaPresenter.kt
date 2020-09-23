package com.example.travada.features.rencana.detailrencana

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRencanaPresenter(val listener: Listener) {

    fun getDetailRencana(){
        TPApiClient.TP_API_SERVICES.getDetailDestination().enqueue(object : Callback<GetDestinasiDetailResponse> {
            override fun onFailure(call: Call<GetDestinasiDetailResponse>, t: Throwable) {
                t.message?.let {
            // isi
                }
            }

            override fun onResponse(call: Call<GetDestinasiDetailResponse>, response: Response<GetDestinasiDetailResponse>) {
                response.body()?.data?.let {
                    listener.implementDetailDestinasi(it.toMutableList())
                }
            }
        })
    }


//        val adapterGambar = GambarWisataAdapter(gambarWisata,this)
//        val linearLayoutGambarWisata =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        listener.showDataGambarWisata(adapterGambar,linearLayoutGambarWisata)


//        val adapterRencanaPerjalanan = RencanaPerjalananAdapter(rencanaPerjalanan)
//        val linearLayoutRencanaPerjalanan = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        listener.showDataRencanaPerjalanan(adapterRencanaPerjalanan,linearLayoutRencanaPerjalanan)



    interface Listener {

//        fun showDataGambarWisata(
//            adapterGambarWisata: GambarWisataAdapter,
//            linearLayoutGambarWisata: LinearLayoutManager) {}
//
//        fun showDataRencanaPerjalanan(
//            adapterRencanaPerjalanan: RencanaPerjalananAdapter,
//            linearLayoutRencanaWisata: LinearLayoutManager) {}


        // fun btnSelengkapnyaDeskripsi()

        // fun btnSelengkapnyaPerjalanan()

        fun implementDetailDestinasi(getDestinasi: MutableList<GetDestinasiDetailResponse.Data>){}


    }
}