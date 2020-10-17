package com.example.travada.features.tabungan.formtabungantiga

import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import com.example.travada.features.tabungan.network.ApiClientTabungan
import com.example.travada.features.tabungan.pojo.GetRekeningTemanResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormTabunganThreePresenter(private val listener: Listener)  {


    fun getRekeningTeman(param : String) {
        ApiClientTabungan.TP_API_SERVICES.getRekeningTeman(param).enqueue(object :
            Callback<GetRekeningTemanResponse> {
            override fun onResponse(call: Call<GetRekeningTemanResponse>, response: Response<GetRekeningTemanResponse>) {
                response.body()?.let {
                    listener.implementLihatRekening(it)
                }
            }

            override fun onFailure(call: Call<GetRekeningTemanResponse>, t: Throwable) {
                t.message?.let {
                    listener.implementLihatRekeningFailure(it)
                }
            }
        })
    }

    fun checked(etNomorRekening: String) {
        if (etNomorRekening.isNotEmpty()) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    interface Listener {

        fun goToTerima()
        fun implementLihatRekening(getRekeningTeman : GetRekeningTemanResponse)
        fun implementLihatRekeningFailure(error : String)
        fun btnActive()
        fun btnInactive()
    }
}