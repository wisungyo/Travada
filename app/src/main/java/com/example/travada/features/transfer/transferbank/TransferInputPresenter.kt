package com.example.travada.features.transfer.transferbank

import android.icu.text.NumberFormat
import com.example.travada.features.topup.TopUpInputActivity
import com.example.travada.features.transfer.transferbank.TransferInputActivity.Companion.saldo
import com.example.travada.features.transfer.transferbank.network.TransferApiClient
import com.example.travada.features.transfer.transferbank.network.TransferApiServices
import com.example.travada.features.transfer.transferbank.pojo.GetUserMeResponse
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class TransferInputPresenter(val listener: Listener) {

    fun checket(nominal: String, accountnum: String) {
        if (nominal.isNotEmpty() && accountnum.isNotEmpty() && TransferInputActivity.isError == false) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    fun checknominal(nominal: Long) {
        if (nominal >= saldo) {
            val localeID =  Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(localeID)
            listener.errTransfernominal("Saldo anda ${numberFormat.format(saldo)}")
            TransferInputActivity.isError = true
        } else {
            listener.errTransfernominal(null)
            TransferInputActivity.isError = false
        }
    }

    fun setInformation() {
        val token = Hawk.get(util.SF_TOKEN, "0")
        listener.showLoadingDialog()
        TransferApiClient.TRANSFER_API_SERVICES.getMe(token).enqueue(object :
            Callback<GetUserMeResponse> {
            override fun onFailure(call: Call<GetUserMeResponse>, t: Throwable) {
                listener.showToast(t.message.toString())
                listener.hideLoadingDialog()
                listener.btnInactive()
            }

            override fun onResponse(
                call: Call<GetUserMeResponse>,
                response: Response<GetUserMeResponse>
            ) {
                response.body()?.data?.let {
                    listener.setInfo(it.namaLengkap)
                    saldo = it.balance
                }
                listener.hideLoadingDialog()
                listener.btnInactive()
            }
        })
    }

    interface Listener{
        fun btnActive()
        fun btnInactive()
        fun goToNextPage(value: Long)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun errAccountnumb(text: String?)
        fun errTransfernominal(text: String?)
        fun showToast(text:String)
        fun setInfo(name:String)
    }
}