package com.example.travada.features.topup.network

import com.example.travada.features.topup.pojo.PostTopupRequest
import com.example.travada.features.topup.pojo.PostTopupResponse
import com.example.travada.fragmentnav.akun.pojo.GetUserMeResponse
import com.example.travada.welcomepage.pojo.PostCheckRegister1Request
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TopUpApiServices {

    @POST("banking/topup")
    fun TopUp(@Header("Authorization") authHeader: String?, @Body body: PostTopupRequest): Call<PostTopupResponse>

    @GET("auth/user/me")
    fun getMe(@Header("Authorization") authHeader: String?): Call<com.example.travada.features.topup.pojo.GetUserMeResponse>

}