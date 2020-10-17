package com.example.travada.features.tabungan.detailtabungan.presenter

import androidx.appcompat.app.AppCompatActivity
import com.example.travada.features.tabungan.adapter.DetailTabunganAdapter
import com.example.travada.features.tabungan.network.ApiClientTabungan
import com.example.travada.features.tabungan.pojo.GetTabunganDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTabunganFragmentPresenter(val listener: Listener) : AppCompatActivity() {

    fun getDetailTabungan(id: Int) {
        listener.showLoadingDialog()
        ApiClientTabungan.TP_API_SERVICES.getDetailTabungan(id).enqueue(object :
            Callback<GetTabunganDetailResponse> {
            override fun onResponse(
                call: Call<GetTabunganDetailResponse>,
                response: Response<GetTabunganDetailResponse>
            ) {
                response.body()?.data?.let {
                    listener.implementDetailTabungan(it)
                    listener.hideLoadingDialog()
                }
            }

            override fun onFailure(call: Call<GetTabunganDetailResponse>, t: Throwable) {
                t.message?.let {
                    listener.implementDetailTabunganFailure(it)
                    listener.hideLoadingDialog()
                }
            }
        })
    }

    interface Listener {
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showData(adapterDetailTabungan: DetailTabunganAdapter)
        fun implementDetailTabungan(getTabungan: GetTabunganDetailResponse.Data)
        fun implementDetailTabunganFailure(errMessage: String)
    }
}