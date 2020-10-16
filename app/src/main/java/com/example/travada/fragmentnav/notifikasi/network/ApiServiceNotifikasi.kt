package com.example.travada.fragmentnav.notifikasi.network

import com.example.travada.fragmentnav.notifikasi.pojo.GetNotifikasiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiServiceNotifikasi {
    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("notif")
    fun getNotifikasi(
        @Header("Authorization") token: String
    ): Call<GetNotifikasiResponse>
}