package com.example.travada.features.rencana.detailrencana.presenter

import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRencanaPresenter(val listener: Listener) {

    fun getDetailRencana(id : Int){
        listener.showLoadingDialog()
        TPApiClient.TP_API_SERVICES.getDestination(id).enqueue(object : Callback<GetDestinasiResponse> {
            override fun onResponse(call: Call<GetDestinasiResponse>, response: Response<GetDestinasiResponse>) {
                response.body()?.data?.let {
                    listener.implementDetailDestinasi(it)
                    listener.hideLoadingDialog()
                }
            }

            override fun onFailure(call: Call<GetDestinasiResponse>, t: Throwable) {
                t.message?.let {
                    listener.implementDetailRencanaFailure(it)
                    listener.hideLoadingDialog()
                }
            }
        })
    }

    fun gambarDetail(detailGambar : String){
        listener.showGambarDetail(detailGambar)
    }

    interface Listener {
        fun btnSelengkapnyaDeskripsi()
        fun btnSelengkapnyaPerjalanan()
        fun implementDetailDestinasi(getDestinasi: GetDestinasiResponse.Data)
        fun showListGambar(gambarList : List<String>)
        fun showListPerjalanan(PerjalananList : List<String>)
        fun showListFasilitas(fasilitasList : List<String>)
        fun showGambarDetail(detailGambar : String)
        fun showInfoSyaratKetentuan(syaratKetentuan : String)
        fun showInfoPersiapan(persiapan : String)
        fun showInfoWaktuCuaca(waktuCuaca : String)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun implementDetailRencanaFailure(errMessage: String)
    }
}