package com.example.travada.features.rencana.searchpage

import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiAllResponse
import com.example.travada.features.rencana.searchpage.TPSearchPageActivity.Companion.benua
import com.example.travada.features.rencana.searchpage.TPSearchPageActivity.Companion.isFilter
import com.example.travada.features.rencana.searchpage.TPSearchPageActivity.Companion.isSearch
import com.example.travada.features.rencana.searchpage.TPSearchPageActivity.Companion.maxValue
import com.example.travada.features.rencana.searchpage.TPSearchPageActivity.Companion.minValue
import com.example.travada.features.rencana.searchpage.TPSearchPageActivity.Companion.searchQuery
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TPSearchResultPresenter(val listener:Listener) {

    fun getAllDestination(){
        listener.showLoadingDialog()
        TPApiClient.TP_API_SERVICES.getalldestination().enqueue(object :
            Callback<GetDestinasiAllResponse> {
            override fun onFailure(call: Call<GetDestinasiAllResponse>, t: Throwable) {
                listener.showToast(t.message.toString())
                listener.hideLoadingDialog()
            }

            override fun onResponse(
                call: Call<GetDestinasiAllResponse>,
                response: Response<GetDestinasiAllResponse>
            ) {
                response.body()?.data?.let {
                    if (it.isEmpty()) {
                        listener.showErrorImage()
                    } else {
                        listener.hideErrorImage()
                        listener.showDestinasiResult(it.toMutableList())
                    }
                }
                listener.hideLoadingDialog()
            }
        })
    }

    fun getSearchDestination(){
        listener.showLoadingDialog()
        TPApiClient.TP_API_SERVICES.getSearchDestination(searchQuery).enqueue(object :
            Callback<GetDestinasiAllResponse> {
            override fun onFailure(call: Call<GetDestinasiAllResponse>, t: Throwable) {
                listener.showToast(t.message.toString())
                listener.hideLoadingDialog()
            }

            override fun onResponse(
                call: Call<GetDestinasiAllResponse>,
                response: Response<GetDestinasiAllResponse>
            ) {
                response.body()?.data?.let {
                    if (it.isEmpty()) {
                        listener.showErrorImage()
                    } else {
                        listener.hideErrorImage()
                        listener.showDestinasiResult(it.toMutableList())
                    }
                }
                listener.hideLoadingDialog()
            }
        })
    }

    fun getFilteredDestination() {
        listener.showLoadingDialog()
        TPApiClient.TP_API_SERVICES.getFilteredDestination(minValue.toString(), maxValue.toString(), benua).enqueue(object :
            Callback<GetDestinasiAllResponse> {
            override fun onFailure(call: Call<GetDestinasiAllResponse>, t: Throwable) {
                listener.showToast(t.message.toString())
                listener.hideLoadingDialog()
            }

            override fun onResponse(
                call: Call<GetDestinasiAllResponse>,
                response: Response<GetDestinasiAllResponse>
            ) {
                response.body()?.data?.let {
                    if (it.isEmpty()) {
                        listener.showErrorImage()
                    } else {
                        listener.hideErrorImage()
                        listener.showDestinasiResult(it.toMutableList())
                    }
                }
                listener.hideLoadingDialog()
            }

        })



    }

    fun checkResult() {
        if (isSearch == false && isFilter == false) {
            getAllDestination()
        } else if (isSearch == true && isFilter == false) {
            getSearchDestination()
        } else if (isSearch == false && isFilter == true) {
            getFilteredDestination()
        }
    }

    interface Listener {
        fun showDestinasiResult(List: MutableList<GetDestinasiAllResponse.Data>)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showToast(text:String)
        fun showErrorImage()
        fun hideErrorImage()
    }
}