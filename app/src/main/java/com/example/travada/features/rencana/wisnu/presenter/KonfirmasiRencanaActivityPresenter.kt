package com.example.travada.features.rencana.wisnu.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.wisnu.adapter.AdapterKonfirmasiRencanaActivity
import com.example.travada.sampeldata.DataCicilanUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KonfirmasiRencanaActivityPresenter (val listener: Listener): AppCompatActivity() {
    private lateinit var listUser: ArrayList<DataCicilanUser>

    fun fetchMainData(position: Int, jumlahOrang: Int) {
        TPApiClient.TP_API_SERVICES.getDestination(position).enqueue(object : Callback<GetDestinasiResponse> {
            override fun onResponse(
                call: Call<GetDestinasiResponse>,
                response: Response<GetDestinasiResponse>
            ) {
//                response.body()?.data?.let {
//                    listener.showMainData(it, jumlahOrang)
//                }
                if (response.isSuccessful && response.body()?.status == "OK") {
                    response.body()?.data?.let { listener.showMainData(it, jumlahOrang) }
                } else {
                    getDataError("Mohon maaf. Ada kesalahan.")
                }
            }

            override fun onFailure(call: Call<GetDestinasiResponse>, t: Throwable) {
                getDataError(t.localizedMessage)
            }
        })
    }

    fun fetchDataCicilan(jumlahOrang: Int, jumlahBiaya: Int) {
        listener.showDataCicilan(jumlahOrang, jumlahBiaya)
        listUser = ArrayList<DataCicilanUser>()
        for (i in 1..jumlahOrang) {
            listUser.add(
                DataCicilanUser(
                    "ABC1230${i}",
                    "Orang $i",
                    "123456${i}",
                    "orang${i}@gmail.com",
                    false
                )
            )
        }
    }

    fun changeCicilanUserStatus(position: Int) {
        listUser[position].status = !listUser[position].status
        fetchDataCicilanLayout()
    }
    
    fun fetchDataCicilanLayout() {
        val adapterKonfirmasiRencana = AdapterKonfirmasiRencanaActivity(listUser, this)
        val linearLayoutKonfirmasiRencana = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        listener.showCicilanList(adapterKonfirmasiRencana, linearLayoutKonfirmasiRencana)
    }

    fun checkNextButtonCondition(jumlahOrang: Int) {
        var condition = true
        for (i in 1..jumlahOrang) {
            if (!listUser[i-1].status) {
                condition = false
            }
        }
        listener.showNextButtonCondition(condition)
    }

    fun getDataError(localizedMessage: String?) {
        listener.showDataError(localizedMessage)
    }

    fun nextButtonClicked() {
        listener.showNextButtonClicked("Detail pemesanan sudah benar?")
    }

    fun doBack() {
        listener.showBack()
    }

    /*
        LISTENER
     */
    interface Listener {
        fun showMainData(data: GetDestinasiResponse.Data, jumlahOrang: Int)
        fun showDataCicilan(jumlahOrang: Int, jumlahBiaya: Int)
        fun showCicilanList(
            adapterKonfirmasiRencanaActivity: AdapterKonfirmasiRencanaActivity,
            linearLayoutManager: LinearLayoutManager
        )
        fun showNextButtonCondition(condition: Boolean)
        fun showNextButtonClicked(title: String)
        fun showDataError(localizedMessage: String?)
        fun showBack()
    }
}