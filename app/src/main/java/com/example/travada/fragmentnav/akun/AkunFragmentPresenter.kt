package com.example.travada.fragmentnav.akun

import com.example.travada.features.rencana.searchpage.room.DatabaseItem
import com.example.travada.fragmentnav.akun.network.AkunApiClient
import com.example.travada.fragmentnav.akun.pojo.GetUserMeResponse
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class AkunFragmentPresenter(val listener: Listener) {

    fun getMe() {
        var token = Hawk.get(util.SF_TOKEN, "0")


        listener.showLoadingDialog()
        AkunApiClient.AKUN_API_SERVICES.getMe(token)
            .enqueue(object : retrofit2.Callback<GetUserMeResponse> {
                override fun onFailure(call: Call<GetUserMeResponse>, t: Throwable) {
                    listener.showToast(t.message.toString())
                    listener.hideLoadingDialog()
                }

                override fun onResponse(
                    call: Call<GetUserMeResponse>,
                    response: Response<GetUserMeResponse>
                ) {
                    response.body()?.data?.let {
                        var accoutnumb = it.noRekening
                        var accountname = it.namaLengkap
                        accoutnumb =
                            StringBuilder(accoutnumb).insert(4, ' ').insert(9, ' ').insert(14, ' ')
                                .toString()
                        listener.setResponse(accountname, accoutnumb)
                    }
                    listener.hideLoadingDialog()
                }

            })
    }

    fun logout(db: DatabaseItem) {
        Hawk.deleteAll()
        GlobalScope.launch {
            db.itemDao().deleteAllHistory()
        }
        listener.goToMenuLogin()
    }


    interface Listener {
        fun goToChangePassword()
        fun goToChangePin()
        fun goToMenuLogin()
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showToast(text: String)
        fun setResponse(text1: String, text2: String)
    }
}