package com.example.travada.features.tabungan.maintabungan

import com.example.travada.features.tabungan.adapter.ListTabunganAdapter
import com.example.travada.features.tabungan.models.DataWisata
import com.example.travada.features.tabungan.network.ApiClientTabungan
import com.example.travada.features.tabungan.pojo.GetAllTabunganResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TabunganPresenter(val listener: Listener) {

    fun dataTabunganList(){
        listener.showLoadingDialog()
        ApiClientTabungan.TP_API_SERVICES.getAllTabungan().enqueue(object : Callback<GetAllTabunganResponse> {
            override fun onFailure(call: Call<GetAllTabunganResponse>, t: Throwable) {
                t.message?.let {
                    listener.implementAllTabunganFailure(it)
                }
                listener.hideLoadingDialog()
            }

            override fun onResponse(call: Call<GetAllTabunganResponse>, response: Response<GetAllTabunganResponse>) {
                response.body()?.data?.let {
                    listener.implementAllTabungan(it.toMutableList()) }
                listener.hideLoadingDialog()
            }
        })
    }

    fun goToDetailTabunganWisata(dataWisata: DataWisata) {
        listener.showDetailTabunganWisata(dataWisata)
    }

    interface Listener {

        fun hideLoadingDialog()

        fun implementAllTabungan(getTabungan: MutableList<GetAllTabunganResponse.Data>)

        fun implementAllTabunganFailure(errMessage: String)

        fun showLoadingDialog()
        fun showData(adapterWisataPilihan: ListTabunganAdapter)
        fun showDetailTabunganWisata(dataWisata: DataWisata)
    }
}