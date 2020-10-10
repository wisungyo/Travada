package com.example.travada.features.rencana.wisnu.presenter

import androidx.appcompat.app.AppCompatActivity
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultRencanaActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchData(idDestinasi: Int) {
        listener.showLoadingDialog()
        TPApiClient.TP_API_SERVICES.getDestination(idDestinasi).enqueue(object :
            Callback<GetDestinasiResponse> {
            override fun onResponse(
                call: Call<GetDestinasiResponse>,
                response: Response<GetDestinasiResponse>
            ) {
                if (response.isSuccessful && response.body()?.status == "OK") {
                    response.body()?.data?.let {
                        listener.showMainData(it)
                    }
                } else {
                    listener.showDataError("Mohon maaf. Ada kesalahan.")
                }
                listener.hideLoadingDialog()
            }

            override fun onFailure(call: Call<GetDestinasiResponse>, t: Throwable) {
                listener.showDataError(t.localizedMessage)
                listener.hideLoadingDialog()
            }
        })
    }

    interface Listener {
        fun showMainData(data: GetDestinasiResponse.Data)
        fun showDataError(error: String)
        fun showLoadingDialog()
        fun hideLoadingDialog()
    }
}