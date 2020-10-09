package com.example.travada.fragmentnav.akun.network

import com.example.travada.fragmentnav.akun.pojo.GetUserMeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface AkunApiServices {

    @GET("auth/user/me")
    fun getMe(@Header("Authorization") authHeader: String?): Call<GetUserMeResponse>

}