package com.example.travada.fragmentnav.notifikasi.presenter

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.travada.fragmentnav.notifikasi.network.ApiClientNotifikasi
import com.example.travada.fragmentnav.notifikasi.pojo.GetDetailNotifikasiResponse
import com.example.travada.fragmentnav.notifikasi.pojo.GetNotifikasiResponse
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailNotifikasiTravaplanPresenter(val listener: Listener) : AppCompatActivity() {

    fun fetchDestinasiData(id: Int) {
        listener.showLoadingDialog()
        val token = Hawk.get(util.SF_TOKEN, "")
        ApiClientNotifikasi.API_SERVICE_NOTIFIKASI.getDetailNotifikasi(token,id).enqueue(object :
            Callback<GetDetailNotifikasiResponse> {
            override fun onResponse(
                call: Call<GetDetailNotifikasiResponse>,
                response: Response<GetDetailNotifikasiResponse>
            ) {
                Log.d("checker","$id")
                response.body()?.data?.let{
                    listener.implementPemesanan(it.pemesanan)
                    listener.implementDestinasi(it.destinasi)
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

        fun implementPemesanan(getPemesanan: GetDetailNotifikasiResponse.Data.Pemesanan?)
        fun implementDestinasi(getDestinasi: GetDetailNotifikasiResponse.Data.Destinasi?)

        fun showDialogGabung(titleGabung: String, subtitleGabung: String)
        fun showDialogTolak(titleTolak: String, subtitleTolak: String)

    }
}