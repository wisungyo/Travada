package com.example.travada.detailriwayat.presenter

import android.util.Log
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.PutBayarCicilan
import com.example.travada.features.rencana.pojo.PutCicilan
import com.example.travada.features.rencana.searchpage.room.DatabaseItem
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PinBayarCicilanActivityPresenter(val listener: Listener) {

    fun pinView(nums: ArrayList<Int>) {


        val sb = StringBuilder()
        for (i in 0 until nums.size) {
            val num = nums[i]
            sb.append(num)
        }
        val result = sb.toString()
        listener.setPinView(result)
        if (result.length >= 6) {
            checkPIN(result)
        } else {
            listener.hideErrorMessage()
        }
    }

    fun checkPIN(result:String){
        val pin_session = Hawk.get(util.SF_SESSION, "000000")
        if(pin_session == result){
            listener.goToMainPageActivity()
        } else {
            listener.showErrorMessage("Pin salah/tidak sesuai. Silahkan coba kembali")
        }
    }

    fun bayarCicilan(id: Int) {
        Log.d("CICILAN", "$id")
        TPApiClient.TP_API_SERVICES.putCicilan(id, "Dibayar").enqueue(object : Callback<PutCicilan> {
            override fun onResponse(call: Call<PutCicilan>, response: Response<PutCicilan>) {
                if (!response.isSuccessful) {
//                    listener.showDataError("Fetching data gagal")
                    return
                }

                bayarCicilanPotongSaldo(id)
            }

            override fun onFailure(call: Call<PutCicilan>, t: Throwable) {

            }

        })


    }

    fun bayarCicilanPotongSaldo(id: Int) {
        Log.d("CICILAN", "$id")
        TPApiClient.TP_API_SERVICES.putBayarCicilan(Hawk.get(util.SF_TOKEN, ""), id).enqueue(object : Callback<PutBayarCicilan> {
            override fun onResponse(
                call: Call<PutBayarCicilan>,
                response: Response<PutBayarCicilan>
            ) {
                if (!response.isSuccessful) {
//                    listener.showDataError("Fetching data gagal")
                    return
                }

                listener.finishPin()
            }

            override fun onFailure(call: Call<PutBayarCicilan>, t: Throwable) {

            }

        })
    }

    fun logout(db: DatabaseItem){
        Hawk.deleteAll()
        GlobalScope.launch {
            db.itemDao().deleteAllHistory()
        }
        listener.goToOnboardingEndActivity()
    }

    interface Listener {
        fun goToOnboardingEndActivity()
        fun goToMainPageActivity()
        fun goToForgetPinActivity()
        fun showErrorMessage(text: String)
        fun hideErrorMessage()
        fun setPinView(numb: String)
        fun finishPin()
    }
}