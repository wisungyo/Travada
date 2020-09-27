package com.example.travada.features.rencana.wisnu.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetCicilanResponse
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.wisnu.adapter.AdapterPesanRencanaActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Math.abs


class PesanRencanaActivityPresenter(val listener: Listener): AppCompatActivity() {

    fun fetchMainData(id: Int) {
        val arraySpinner = ArrayList<String>()
        listener.showProgressDialog()
        TPApiClient.TP_API_SERVICES.getDestination(id).enqueue(object : Callback<GetDestinasiResponse> {
            override fun onResponse(
                call: Call<GetDestinasiResponse>,
                response: Response<GetDestinasiResponse>
            ) {
                if (response.isSuccessful && response.body()?.status == "OK") {
                    response.body()?.data?.let {
                        listener.showMainData(it)
                        arraySpinner.add(it.berangkat)
                        listener.showSpinner(arraySpinner)
                    }
                } else {
                    getDataError("Mohon maaf. Ada kesalahan.")
                }
                listener.dismissProgressDialog()
            }

            override fun onFailure(call: Call<GetDestinasiResponse>, t: Throwable) {
                getDataError(t.localizedMessage)
//                listener.dismissProgressDialog()
                listener.dismissProgressDialog()
            }

        })
    }

    fun fetchCicilanData(id: Int, jumlahOrang: Int) {
        TPApiClient.TP_API_SERVICES.getCicilan(id, jumlahOrang).enqueue(object : Callback<GetCicilanResponse> {
            override fun onResponse(
                call: Call<GetCicilanResponse>,
                response: Response<GetCicilanResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        getAdapterCicilan(it)

                        var jumlahBiaya = 0
                        for (i in 0..it.size-1) {
                            jumlahBiaya += kotlin.math.abs(it[i].jumlah)
                        }
                        listener.showJumlahBiaya(jumlahBiaya)
                    }
                } else {
                    getDataError("Mohon maaf. Ada kesalahan.")
                }
            }

            override fun onFailure(call: Call<GetCicilanResponse>, t: Throwable) {
                getDataError(t.localizedMessage)
//                listener.dismissProgressDialog()
            }
        })

    }

    fun getAdapterCicilan(data: List<GetCicilanResponse.Data>) {
        val adapterRencanaPesan = AdapterPesanRencanaActivity(data, this)
        val linearLayoutRencanaPesan = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        listener.showCicilanData(adapterRencanaPesan, linearLayoutRencanaPesan)
    }

    fun getDataError(localizedMessage: String?) {
        listener.showDataError(localizedMessage)
    }

    fun doBack() {
        listener.showBack()
    }

    fun addBiaya(addBiaya: Int) {
        listener.addBiaya(addBiaya)
    }

    fun addOrang(jumlahOrang: Int) {
        listener.showAddOrang(jumlahOrang + 1)
    }

    fun minOrang(jumlahOrang: Int) {
        var jumlahOrangNew = jumlahOrang - 1
        if (jumlahOrangNew <= 1) jumlahOrangNew = 1
        listener.showMinOrang(jumlahOrangNew)
    }

    fun doKonfirmasi(intentPosition: Int) {
        listener.showKonfirmasi(intentPosition)
    }


    /*
        INTERFACE
    */
    interface Listener {
        fun showMainData(data: GetDestinasiResponse.Data)
        fun showSpinner(arraySpinner: ArrayList<String>)
        fun showCicilanData(adapter: AdapterPesanRencanaActivity, layout: LinearLayoutManager)
        fun showDataError(localizedMessage: String?)
        fun showBack()
        fun addBiaya(addBiaya: Int)
        fun showAddOrang(addOrang: Int)
        fun showMinOrang(addOrang: Int)
        fun showKonfirmasi(intentPosition: Int)
        fun showJumlahBiaya(jumlahBiaya: Int)
        fun showProgressDialog()
        fun dismissProgressDialog()
    }
}