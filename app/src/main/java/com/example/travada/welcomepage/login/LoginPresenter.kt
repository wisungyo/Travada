package com.example.travada.welcomepage.login

import com.example.travada.welcomepage.login.LoginActivity.Companion.isError
import com.example.travada.welcomepage.network.ApiClient
import com.example.travada.welcomepage.pojo.PostLoginBody
import com.example.travada.welcomepage.pojo.PostLoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val listener: Listener) {

    fun loginCheck(username: String, pass:String){
        val login = PostLoginBody(
            username, pass
        )

        listener.showLoadingDialog()
        ApiClient.apiServices.Login(login).enqueue(object : Callback<PostLoginResponse> {
            override fun onResponse(
                call: Call<PostLoginResponse>,
                response: Response<PostLoginResponse>
            ) {
                response.body()?.let {
                    if (it.status == "OK") {
                        listener.goToNextPage()
                    } else {
                        listener.showErrorPassword(it.message)
                        listener.showErrorUsername(it.message)

                    }
                    listener.hideLoadingDialog()
                }
            }

            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                t.message?.let {
                    listener.showErrorUsername(it)
                    listener.showErrorPassword(it)
                }
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

    fun checkusername(username: String){
        if (username.isEmpty()){
            listener.showErrorUsername("Username tidak boleh kosong")
            isError = true
        } else {
            listener.hideErrorUsername()
            isError = false
        }
    }

    fun checkpass(pass: String){
        if (pass.length <=7 ){
            listener.showErrorPassword("Password minimal 8 karakter")
            isError = true
        } else {
            listener.hideErrorPassword()
            isError = false
        }
    }

    fun goToNextPage(){
        listener.goToNextPage()
    }

    fun goToForgetPassPage(){
        listener.goToForgotPassPage()
    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun goToNextPage()
        fun goToForgotPassPage()
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showErrorUsername(text:String)
        fun hideErrorUsername()
        fun showErrorPassword(text:String)
        fun hideErrorPassword()
    }
}