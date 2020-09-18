package com.example.travada.welcomepage.network

import com.example.travada.welcomepage.pojo.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    //Method Post
    @POST("auth/login")
    fun Login(@Body body: PostLoginRequest): Call<PostLoginResponse>

    @POST("auth/register/check")
    fun checkRegister1(@Body body: PostCheckRegister1Request): Call<PostCheckRegister1Response>

    @POST("auth/register")
    fun registerfinal(@Body body: RequestBody): Call<PostRegisterResponse>

    @POST("auth/confirm")
    fun confirm(@Body body: PostConfirmRequest): Call<PostConfirmResponse>

    @POST("auth/resend")
    fun resend(@Body body: PostResendRequest): Call<PostResendResponse>
}