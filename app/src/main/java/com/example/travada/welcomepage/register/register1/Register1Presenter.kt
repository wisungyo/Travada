package com.example.travada.welcomepage.register.register1

import android.util.Patterns
import com.example.travada.welcomepage.login.LoginActivity
import com.example.travada.welcomepage.network.WPApiClient
import com.example.travada.welcomepage.pojo.PostCheckRegister1Request
import com.example.travada.welcomepage.pojo.PostCheckRegister1Response
import com.example.travada.welcomepage.register.register1.Register1Activity.Companion.isError
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register1Presenter(val listener: Register1Presenter.Listener) {

    fun checkData(text1: String, text2: String, text3: String, text4: String) {
        val register1 = PostCheckRegister1Request(
            text1,text2,text3,text4
        )

        listener.showLoadingDialog()
        WPApiClient.WP_API_SERVICES.checkRegister1(register1).enqueue(object : Callback<PostCheckRegister1Response> {
            override fun onFailure(call: Call<PostCheckRegister1Response>, t: Throwable) {
                listener.showToast(t.message.toString())
                listener.hideLoadingDialog()
            }

            override fun onResponse(
                call: Call<PostCheckRegister1Response>,
                response: Response<PostCheckRegister1Response>
            ) {
                if (response.isSuccessful && response.body()?.status == "OK") {
                    listener.goToNextPage()
                } else {
                    val errorResponse: PostCheckRegister1Response = Gson().fromJson(
                        response.errorBody()?.string(),
                        PostCheckRegister1Response::class.java
                    )
                    listener.errUsername(errorResponse.data.msg1)
                    listener.errEmail(errorResponse.data.msg2)
                    listener.errPhone(errorResponse.data.msg3)
                    listener.errAccountnumb(errorResponse.data.msg4)
                    LoginActivity.isError = true
                    listener.btnInactive()
                }
                listener.hideLoadingDialog()
            }
        })
    }

    fun checket(name: String, email: String, username:String, phone:String, accountNumb:String) {
        if (name.isNotEmpty() && email.isNotEmpty() && username.isNotEmpty() && phone.isNotEmpty() && accountNumb.isNotEmpty() && isError == false) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    fun checkName(text: String){
        if(text.isNotEmpty()){
            listener.errName(null)
            isError = false
        } else {
            listener.errName("Nama tidak boleh kosong" )
            isError = true
        }
    }

    fun checkAccountnumb(text: String){
        if(text.isNotEmpty()){
            listener.errAccountnumb(null)
            isError = false
        } else {
            listener.errAccountnumb("Nama tidak boleh kosong" )
            isError = true
        }
    }

    fun checkEmail(text: String){
        if(Patterns.EMAIL_ADDRESS.matcher(text).matches()){
            listener.errEmail(null)
            isError = false
        } else {
            listener.errEmail("Format email tidak valid" )
            isError = true
        }
    }

    fun checkUsername(text: String){
        if(text.length>=4){
            listener.errUsername(null)
            isError = false
        } else {
            listener.errUsername("Username minimal 4 karakter" )
            isError = true
        }
    }

    fun checkPhone(text: String){
        if(text.length<=9){
            listener.errPhone("Nomer handphone minimal 10 karakter" )
            isError = true
        } else if (text.length>12){
            listener.errPhone("Nomer handphone maksimal 13 karakter" )
            isError = true
        }else {
            listener.errPhone(null)
            isError = false
        }
    }

    fun goToNextPage() {
        listener.goToNextPage()
    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun goToNextPage()
        fun btnBack()
        fun errName(message: String?)
        fun errEmail(message: String?)
        fun errUsername(message: String?)
        fun errPhone(message: String?)
        fun errAccountnumb(message: String?)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showToast(text:String)

    }
}