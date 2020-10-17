package com.example.travada.features.transfer.transferbank

import android.os.Bundle
import com.example.travada.features.transfer.transferbank.network.TransferApiClient
import com.example.travada.features.transfer.transferbank.pojo.PostTransferRequest
import com.example.travada.features.transfer.transferbank.pojo.PostTransferResponse
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransferPinPresenter(val listener: Listener) {

    fun pinView(nums: ArrayList<Int>, bundle: Bundle) {
        val sb = StringBuilder()
        for (i in 0 until nums.size) {
            val num = nums[i]
            sb.append(num)
        }
        val result = sb.toString()
        listener.setPinView(result)
        if (result.length == 6) {
            checkPIN(result, bundle)
        } else {
            listener.hideErrorMessage()
        }
    }

    fun checkPIN(result:String, bundle: Bundle){
        val nominal = bundle.getString("nominal").toString()
        val accountnumb = bundle.getString("accountnumb").toString()
        val note = bundle.getString("note").toString()
        val token = Hawk.get(util.SF_TOKEN, "0")

        val bodytransfer = PostTransferRequest(
            note, nominal, result, accountnumb
        )

        listener.showLoadingDialog()
        TransferApiClient.TRANSFER_API_SERVICES.Transfer(token, bodytransfer).enqueue(object :
            Callback<PostTransferResponse> {
            override fun onFailure(call: Call<PostTransferResponse>, t: Throwable) {
                listener.showToast(t.message.toString())
                listener.hideLoadingDialog()
            }

            override fun onResponse(
                call: Call<PostTransferResponse>,
                response: Response<PostTransferResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        bundle.putString("createdAt", it.createdAt)
                        bundle.putString("id", it.id.toString())
                        bundle.putString("namaAsal", it.namaAsal)
                        bundle.putString("namaTujuan", it.namaTujuan)
                        bundle.putString("bankAsal", it.bankAsal)
                        bundle.putString("rekeningAsal", it.rekeningAsal)
                        bundle.putString("bankTujuan", it.bankTujuan)
                        bundle.putString("rekeningTujuan", it.rekeningTujuan)

                        listener.goToInvoiceActivity(bundle)
                    }
                } else {
                    listener.showErrorMessage("Pin yang anda masukkan tidak sesuai")
                }

                listener.hideLoadingDialog()
            }
        })
    }

    interface Listener {
        fun goToInvoiceActivity(bundle: Bundle)
        fun showErrorMessage(text: String)
        fun hideErrorMessage()
        fun setPinView(numb: String)
        fun showToast(text:String)
        fun showLoadingDialog()
        fun hideLoadingDialog()
    }
}