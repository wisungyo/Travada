package com.example.travada.fragmentnav.akun.network

import com.example.travada.fragmentnav.akun.pojo.GetUserMeResponse
import com.example.travada.fragmentnav.akun.pojo.coba
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface AkunApiServices {

    @GET("auth/user/me")
    fun getMe(@Header("Authorization") authHeader: String?): Call<GetUserMeResponse>

//    @GET("notif/detail/{id}")
//    fun getcoba(@Header("Authorization") authHeader: String?, @Path("id")id:Int): Call<coba>
}