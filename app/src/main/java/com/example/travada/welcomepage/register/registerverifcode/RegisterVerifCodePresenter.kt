package com.example.travada.welcomepage.register.registerverifcode

import android.content.SharedPreferences
import android.os.Bundle
import com.example.travada.welcomepage.network.WPApiClient
import com.example.travada.welcomepage.pojo.PostConfirmRequest
import com.example.travada.welcomepage.pojo.PostConfirmResponse
import com.example.travada.welcomepage.pojo.PostResendRequest
import com.example.travada.welcomepage.pojo.PostResendResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.CountDownTimer
import android.util.Base64
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import java.nio.charset.StandardCharsets

class RegisterVerifCodePresenter(val listener: Listener) {

    fun checket(text:String){
        if(text.length == 4) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    fun checkVerif(bundle: Bundle, code: String) {
        lateinit var username: String
        lateinit var password:String


        bundle?.let {
            password = it.getString("password").toString()
            username = it.getString("username").toString()
        }

        val confirm = PostConfirmRequest(
            code, password, username
        )


        listener.showLoadingDialog()
        WPApiClient.WP_API_SERVICES.confirm(confirm).enqueue(object : Callback<PostConfirmResponse> {
            override fun onFailure(call: Call<PostConfirmResponse>, t: Throwable) {
                listener.showToast(t.message.toString())
                listener.hideLoadingDialog()
            }

            override fun onResponse(
                call: Call<PostConfirmResponse>,
                response: Response<PostConfirmResponse>
            ) {
                if (response.isSuccessful) {
//                    val editor = sf.edit()
//                    editor.putString(util.SF_TOKEN, response.body()?.data?.token)
//                    editor.putString(util.SF_TOKEN_TYPE, response.body()?.data?.tokenType)
//                    editor.putString(util.SF_PIN, response.body()?.data?.pin)
//                    editor.putBoolean(util.SF_ISLOGIN, true)
//                    editor.apply()
                    val data: ByteArray =
                        Base64.decode(response.body()?.data?.session, Base64.DEFAULT)
                    val pin = String(data, StandardCharsets.UTF_8)

                    Hawk.put(util.SF_SESSION, pin)
                    Hawk.put(util.SF_TOKEN, "${response.body()?.data?.tokenType} ${response.body()?.data?.token}")
                    Hawk.put(util.SF_ISLOGIN, true)
                    listener.goToNextPage(bundle)
                } else {
                    listener.ShowErrorMessage()
                    listener.btnInactive()
                }
                listener.hideLoadingDialog()
            }
        })
    }

    fun sendAgain(bundle:Bundle){
        lateinit var email:String

        bundle?.let {
            email = it.getString("email").toString()
        }

        val resend= PostResendRequest (
            email
        )

        listener.showLoadingDialog()
        WPApiClient.WP_API_SERVICES.resend(resend).enqueue(object : Callback<PostResendResponse> {
            override fun onFailure(call: Call<PostResendResponse>, t: Throwable) {
                listener.showToast(t.message.toString())
                listener.hideLoadingDialog()
            }

            override fun onResponse(
                call: Call<PostResendResponse>,
                response: Response<PostResendResponse>
            ) {
                if (response.isSuccessful) {
                    listener.showCountdown()
                    startTimer()
                }
                listener.hideLoadingDialog()
            }
        })
    }

    fun startTimer() {
        lateinit var countdown_timer: CountDownTimer
        var time = 60000L

        countdown_timer = object : CountDownTimer(time, 1000) {
            override fun onFinish() {
                listener.hideCountdown()
            }

            override fun onTick(p0: Long) {
                val seconds = (p0 / 1000) % 60
                listener.countdownTimerUI(seconds)
            }
        }
        countdown_timer.start()
    }


    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun ShowErrorMessage()
        fun hideErrorMessage()
        fun goToNextPage(bundle: Bundle)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showToast(text: String)
        fun showCountdown()
        fun hideCountdown()
        fun countdownTimerUI(count: Long)
    }
}