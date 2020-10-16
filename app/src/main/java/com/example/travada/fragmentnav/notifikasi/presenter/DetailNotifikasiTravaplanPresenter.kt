package com.example.travada.fragmentnav.notifikasi.presenter

import androidx.appcompat.app.AppCompatActivity
import com.example.travada.fragmentnav.notifikasi.network.ApiClientNotifikasi
import com.example.travada.fragmentnav.notifikasi.pojo.GetDetailNotifikasiResponse
import com.example.travada.fragmentnav.notifikasi.pojo.GetNotifikasiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailNotifikasiTravaplanPresenter(val listener: Listener) : AppCompatActivity() {

    fun fetchDestinasiData(id: Int) {
        listener.showLoadingDialog()
        ApiClientNotifikasi.API_SERVICE_NOTIFIKASI.getDetailNotifikasi(id).enqueue(object :
            Callback<GetDetailNotifikasiResponse> {
            override fun onResponse(
                call: Call<GetDetailNotifikasiResponse>,
                response: Response<GetDetailNotifikasiResponse>
            ) {

                response.body()?.data?.destinasi?.let {
                    listener.implementDestinasi(it)
                }

                response.body()?.data?.pemesanan?.let {
                    listener.implementPemesanan(it)
//                    listener.hideLoadingDialog()
                }

                listener.hideLoadingDialog()
            }
            override fun onFailure(call: Call<GetDetailNotifikasiResponse>, t: Throwable) {
                t.message?.let {
                    listener.implementDetailRencanaFailure(it)
                    listener.hideLoadingDialog()
                }
            }
        })
    }

    fun GabungClicked() {
        listener.showDialogGabung("Konfirmasi Pemesanan", "Detail pemesanan sudah benar?")
    }

    fun TolakClicked() {
        listener.showDialogTolak("Konfirmasi Pemesanan", "Detail pemesanan sudah benar?")
    }

    interface Listener {

        fun showLoadingDialog()
        fun hideLoadingDialog()

        fun implementDetailRencanaFailure(errMessage: String)
        fun implementPemesanan(getPemesanan: GetDetailNotifikasiResponse.Data.Pemesanan)
        fun implementDestinasi(getDestinasi: GetDetailNotifikasiResponse.Data.Destinasi)

        fun showDialogGabung(titleGabung: String, subtitleGabung: String)
        fun showDialogTolak(titleTolak: String, subtitleTolak: String)

    }
}