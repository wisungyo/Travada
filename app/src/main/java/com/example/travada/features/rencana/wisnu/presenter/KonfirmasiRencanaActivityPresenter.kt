package com.example.travada.features.rencana.wisnu.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.pojo.PostPemesananResponse
import com.example.travada.features.rencana.wisnu.adapter.AdapterKonfirmasiRencanaActivity
import com.example.travada.sampeldata.DataCicilanUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KonfirmasiRencanaActivityPresenter (val listener: Listener): AppCompatActivity() {
    private lateinit var listUser: ArrayList<DataCicilanUser>

    fun fetchMainData(idDestinasi: Int, jumlahOrang: Int) {
        listener.showProgressDialog()
        TPApiClient.TP_API_SERVICES.getDestination(idDestinasi).enqueue(object : Callback<GetDestinasiResponse> {
            override fun onResponse(
                call: Call<GetDestinasiResponse>,
                response: Response<GetDestinasiResponse>
            ) {
//                response.body()?.data?.let {
//                    listener.showMainData(it, jumlahOrang)
//                }
                if (response.isSuccessful && response.body()?.status == "OK") {
                    response.body()?.data?.let {
                        listener.showMainData(it, jumlahOrang)

                    }
                } else {
                    getDataError("Mohon maaf. Ada kesalahan.")
                }
                listener.dismissProgressDialog()
            }

            override fun onFailure(call: Call<GetDestinasiResponse>, t: Throwable) {
                getDataError(t.localizedMessage)
                listener.dismissProgressDialog()
            }
        })
    }

    fun fetchDetailPemesanan(jumlahOrang: Int, jumlahBiaya: Int) {
        listener.showDataCicilan(jumlahOrang, jumlahBiaya)
        listUser = ArrayList<DataCicilanUser>()
        for (i in 0..jumlahOrang-1) {
            listUser.add(
                DataCicilanUser(
                    i,
                    "Orang $i",
                    "123456${i}",
                    "orang${i}@gmail.com",
                    "uriKTP",
                    "uriPassport",
                    false
                )
            )
        }
    }

    fun updateList(id: Int, name: String, phone: String, email: String, uriKTP: String, uriPassword: String) {
        val newSet = DataCicilanUser(id, name, phone, email, uriKTP, uriPassword, true)
        listUser[id] = newSet
    }

    fun doDetailOrang(orang: Int) {
        listener.showDetailOrang(orang)
    }
    
    fun fetchDetailPemesananLayout() {
        val adapterKonfirmasiRencana = AdapterKonfirmasiRencanaActivity(listUser, this)
        val linearLayoutKonfirmasiRencana = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        listener.showCicilanList(adapterKonfirmasiRencana, linearLayoutKonfirmasiRencana)
    }

    fun checkNextButtonCondition() {
        val jumlahOrang = listUser.size

        var condition = true
        for (i in 0..jumlahOrang-1) {
            if (!listUser[i].status) {
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

    fun postPemesananData() {
        listener.doPostPemesanan(listUser)
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
        fun showDetailOrang(orang: Int)
        fun showCicilanList(
            adapterKonfirmasiRencanaActivity: AdapterKonfirmasiRencanaActivity,
            linearLayoutManager: LinearLayoutManager
        )
        fun showNextButtonCondition(condition: Boolean)
        fun showNextButtonClicked(title: String)
        fun showResultRencana(data: PostPemesananResponse.Data)
        fun doPostPemesanan(listUser: ArrayList<DataCicilanUser>)
        fun showDataError(localizedMessage: String?)
        fun showBack()
        fun showProgressDialog()
        fun dismissProgressDialog()
    }
}