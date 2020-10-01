package com.example.travada.welcomepage.login

import android.util.Base64
import android.util.Log
import com.example.travada.util.util
import com.example.travada.welcomepage.login.LoginActivity.Companion.isError
import com.example.travada.welcomepage.network.WPApiClient
import com.example.travada.welcomepage.pojo.PostLoginRequest
import com.example.travada.welcomepage.pojo.PostLoginResponse
import com.google.gson.Gson
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.StandardCharsets

class LoginPresenter(val listener: Listener) {


    fun loginCheck(username: String, pass: String) {
        val login = PostLoginRequest(
            pass, username
        )

        listener.showLoadingDialog()
        WPApiClient.WP_API_SERVICES.Login(login).enqueue(object : Callback<PostLoginResponse> {
            override fun onResponse(
                call: Call<PostLoginResponse>,
                response: Response<PostLoginResponse>
            ) {
                if (response.isSuccessful && response.body()?.status == "OK") {
//                    val editor = sf.edit()
//                    editor.putString(util.SF_TOKEN, response.body()?.data?.token)
//                    editor.putString(util.SF_TOKEN_TYPE, response.body()?.data?.tokenType)
//                    editor.putString(util.SF_PIN, response.body()?.data?.session)
//                    editor.putBoolean(util.SF_ISLOGIN, true)
//                    editor.apply()

                    val data: ByteArray =
                        Base64.decode(response.body()?.data?.session, Base64.DEFAULT)
                    val pin = String(data, StandardCharsets.UTF_8)

                    Hawk.put(util.SF_SESSION, pin)
                    Hawk.put(util.SF_TOKEN, "${response.body()?.data?.tokenType} ${response.body()?.data?.token}")
                    Hawk.put(util.SF_ISLOGIN, true)
                    listener.goToNextPage()

                } else {
                    val errorResponse: PostLoginResponse = Gson().fromJson(
                        response.errorBody()?.string(),
                        PostLoginResponse::class.java
                    )
                    listener.showErrorPassword(errorResponse.message)
                    listener.showErrorUsername(errorResponse.message)
                    isError = true
                    listener.btnInactive()
                }
                listener.hideLoadingDialog()
            }


            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                listener.showToast(t.message.toString())
                listener.hideLoadingDialog()
            }
        })
    }

    fun checket(username: String, pass: String) {
        if (username.isNotEmpty() && pass.length >= 8 && isError == false) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    fun checkusername(username: String) {
        if (username.isEmpty()) {
            listener.showErrorUsername("Username tidak boleh kosong")
            isError = true
        } else {
            listener.hideErrorUsername()
            isError = false
        }
    }

    fun checkpass(pass: String) {
        if (pass.length <= 7) {
            listener.showErrorPassword("Password minimal 8 karakter")
            isError = true
        } else {
            listener.hideErrorPassword()
            isError = false
        }
    }

    fun goToNextPage() {
        listener.goToNextPage()
    }

    fun goToForgetPassPage() {
        listener.goToForgotPassPage()
    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun goToNextPage()
        fun goToForgotPassPage()
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showErrorUsername(text: String?)
        fun hideErrorUsername()
        fun showErrorPassword(text: String?)
        fun hideErrorPassword()
        fun showToast(text:String)
    }
}