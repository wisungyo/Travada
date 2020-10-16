package com.example.travada.fragmentnav.notifikasi

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.fragmentnav.notifikasi.adapter.NotifikasiAdapter
import com.example.travada.fragmentnav.notifikasi.model.DataNotifikasi
import com.example.travada.fragmentnav.notifikasi.network.ApiClientNotifikasi
import com.example.travada.fragmentnav.notifikasi.pojo.GetNotifikasiResponse
import com.example.travada.fragmentnav.riwayat.adapter.AdapterRiwayatProses
import com.example.travada.fragmentnav.riwayat.fragmentriwayat.ProsesFragmentPresenter
import com.example.travada.fragmentnav.riwayat.pojo.GetPemesananRiwayatResponse
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.list_notifikasi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotifikasiFragmentPresenter(val listener: Listener) : AppCompatActivity() {

    fun fetchDataNotifikasi() {
        listener.showLoadingDialog()
        val token = Hawk.get(util.SF_TOKEN, "")
        ApiClientNotifikasi.API_SERVICE_NOTIFIKASI.getNotifikasi(token).enqueue(object :
            Callback<GetNotifikasiResponse> {
            override fun onResponse(
                call: Call<GetNotifikasiResponse>,
                response: Response<GetNotifikasiResponse>
            ) {
                if (response.isSuccessful && response.body()?.status == "OK") {
                    response.body()?.data?.let { getNotifikasi(it) }
                } else {
                    getDataError("Mohon maaf. Ada kesalahan.")
                }
                listener.hideLoadingDialog()
            }

            override fun onFailure(call: Call<GetNotifikasiResponse>, t: Throwable) {
                listener.showDataError(t.toString())
            }
        })
    }

    fun getNotifikasi(it :List<GetNotifikasiResponse.Data>){
        val adapterNotifikasi = NotifikasiAdapter(it,this)
        val linearLayotNotifikasi = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        listener.showDataNotifikasi(adapterNotifikasi,linearLayotNotifikasi)
    }

    fun getDataError(errorMessage: String?) {
        listener.showDataError(errorMessage)
    }

    fun goToDetailNotifikasi(id: Int) {
        listener.showDetaiNotifikasi(id)
    }

    interface Listener {
        fun showDataNotifikasi(
            notifikasiAdapter: NotifikasiAdapter,
            linearLayoutNotifikasi: LinearLayoutManager
        )
        fun showDetaiNotifikasi(id: Int)
        fun showDataError(error: String?)
        fun showLoadingDialog()
        fun hideLoadingDialog()
    }

}