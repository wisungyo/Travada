package com.example.travada.features.rencana.detailrencana

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import com.example.travada.sampeldata.DataCicilanUser
import kotlinx.android.synthetic.main.activity_detail_rencana.*
import kotlinx.android.synthetic.main.activity_detail_rencana.view.*
import kotlinx.android.synthetic.main.activity_detail_rencana.view.ivDetailGambar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRencanaPresenter(val listener: Listener) : AppCompatActivity() {

    fun getDetailRencana(id : Int){
        TPApiClient.TP_API_SERVICES.getDetailDestination(93).enqueue(object : Callback<GetDestinasiDetailResponse> {
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

    fun gambarDetail(detailGambar : String){
        listener.gambarDetail(detailGambar)
    }

    interface Listener {
        fun btnSelengkapnyaDeskripsi()
        fun btnSelengkapnyaPerjalanan()
        fun implementDetailDestinasi(getDestinasi: GetDestinasiDetailResponse.Data){}
        fun listGambar(gambarList : List<String>){}
        fun listPerjalanan(PerjalananList : List<String>){}
        fun listFasilitas(fasilitasList : List<String>){}
        fun gambarDetail(detailGambar : String){}
        fun infoSyaratKetentuan(syaratKetentuan : String){}
        fun infoPersiapan(persiapan : String){}
        fun infoWaktuCuaca(waktuCuaca : String){}
    }
}