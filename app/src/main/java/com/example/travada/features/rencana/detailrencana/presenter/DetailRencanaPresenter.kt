package com.example.travada.features.rencana.detailrencana.presenter

import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRencanaPresenter(val listener: Listener) {

    fun getDetailRencana(id : Int){
        listener.showLoadingDialog()
        TPApiClient.TP_API_SERVICES.getDetailDestination(id).enqueue(object : Callback<GetDestinasiDetailResponse> {

            override fun onResponse(call: Call<GetDestinasiDetailResponse>, response: Response<GetDestinasiDetailResponse>) {
                response.body()?.data?.let {
                    listener.implementDetailDestinasi(it)
                    listener.hideLoadingDialog()
                }
            }

            override fun onFailure(call: Call<GetDestinasiDetailResponse>, t: Throwable) {
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
        fun implementDetailDestinasi(getDestinasi: GetDestinasiDetailResponse.Data)
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