package com.example.travada.features.topup

import com.example.travada.features.topup.TopUpInputActivity.Companion.isError
import com.example.travada.features.topup.network.TopUpApiClient
import com.example.travada.features.topup.pojo.GetUserMeResponse
import com.example.travada.util.util
import com.example.travada.welcomepage.login.LoginActivity
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopUpInputPresenter(val listener: Listener) {

    fun showErrorAccountnumb() {
        if (isError == true) {
            listener.errAccountnumb("Nomor rekening yang dimasukkan salah")
        }
    }

    fun checket(nominal: String, accountnum: String) {
        if (nominal.isNotEmpty() && accountnum.isNotEmpty() && isError == false) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    fun checknominal(nominal: Long) {
        if (nominal <= 50000) {
            listener.errNominal("Top up minimal RP 50.000")
            isError = true
        } else {
            listener.errNominal(null)
            isError = false
        }
    }

    fun setInformation() {
        val token = Hawk.get(util.SF_TOKEN, "0")
        listener.showLoadingDialog()
        TopUpApiClient.TOPUP_API_SERVICES.getMe(token)
            .enqueue(object : Callback<GetUserMeResponse> {
                override fun onFailure(call: Call<GetUserMeResponse>, t: Throwable) {
                    listener.showToast(t.message.toString())
                    listener.hideLoadingDialog()
                }

                override fun onResponse(
                    call: Call<GetUserMeResponse>,
                    response: Response<GetUserMeResponse>
                ) {
                    response.body()?.data?.let {
                        listener.setInformation(
                            StringBuilder(it.noRekening).insert(4, ' ').insert(9, ' ')
                                .insert(14, ' ')
                                .toString(), it.namaLengkap
                        )
                    }
                    listener.hideLoadingDialog()
                }
            })
    }


    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun goToNextPage(value: Long)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun errAccountnumb(text: String?)
        fun errNominal(text: String?)
        fun showToast(text: String)
        fun showBottomSheetDialog(value: Long)
        fun setInformation(numb: String, name: String)
    }
}