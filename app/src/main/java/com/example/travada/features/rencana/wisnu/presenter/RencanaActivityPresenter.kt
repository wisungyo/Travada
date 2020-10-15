package com.example.travada.features.rencana.wisnu.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetPilihanResponse
import com.example.travada.features.rencana.pojo.GetPopulerResponse
import com.example.travada.features.rencana.wisnu.adapter.AdapterTripPopulerRencanaActivity
import com.example.travada.features.rencana.wisnu.adapter.AdapterTripRencanaActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RencanaActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchData() {
        /*
        GET POPULER TRIP DATA FROM API */
        listener.showLoadingDialog()
        TPApiClient.TP_API_SERVICES.getPopulerDestination().enqueue(object : Callback<GetPopulerResponse> {
            override fun onResponse(
                call: Call<GetPopulerResponse>,
                response: Response<GetPopulerResponse>
            ) {
                if (response.isSuccessful && response.body()?.status == "OK") {
                    response.body()?.data?.let { getAdapterTripPopuler(it) }
                } else {
                    getDataError("Mohon maaf. Ada kesalahan.")
                }
//                listener.hideLoadingDialog()
            }

            override fun onFailure(call: Call<GetPopulerResponse>, t: Throwable) {
                getDataError(t.localizedMessage)
//                listener.hideLoadingDialog()
            }
        })

        /*
        GET PILIHAN TRIP DATA FROM API */
//        listener.showLoadingDialog()
        TPApiClient.TP_API_SERVICES.getPilihanDestination().enqueue(object : Callback<GetPilihanResponse> {
            override fun onResponse(
                call: Call<GetPilihanResponse>,
                response: Response<GetPilihanResponse>
            ) {
                response.body()?.data?.let {
                    getAdapterTripPilihan(it)
                }
                listener.hideLoadingDialog()
            }

            override fun onFailure(call: Call<GetPilihanResponse>, t: Throwable) {
                getDataError(t.localizedMessage)
                listener.hideLoadingDialog()
            }
        })
    }

    private fun getAdapterTripPilihan(it: List<GetPilihanResponse.Data>) {
        val adapterTripPilihan = AdapterTripRencanaActivity(it, this)
        val linearLayoutTripPilihan = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        listener.showTripPilihan(
            adapterTripPilihan,
            linearLayoutTripPilihan
        )
    }

    fun getAdapterTripPopuler(it: List<GetPopulerResponse.Data>) {
        val adapterTripPopuler = AdapterTripPopulerRencanaActivity(it, this)
        val linearLayoutTripPopuler = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        listener.showTripPopuler(
            adapterTripPopuler,
            linearLayoutTripPopuler
        )
    }

    fun getDataError(localizedMessage: String?) {
        listener.showDataError(localizedMessage)
    }

    fun doBack() {
        listener.showBack()
    }

    fun itemClicked(position: Int) {
        listener.showItemClicked(position)
    }

    interface Listener {
        fun showTripPilihan(
            adapterTrip: AdapterTripRencanaActivity,
            linearLayoutTrip: LinearLayoutManager
        )
        fun showTripPopuler(
            adapterTripPopuler: AdapterTripPopulerRencanaActivity,
            linearLayoutTripPopuler: LinearLayoutManager
        )
        fun showBack()
        fun showItemClicked(position: Int)
        fun showDataError(localizedMessage: String?)
        fun showLoadingDialog()
        fun hideLoadingDialog()
    }
}