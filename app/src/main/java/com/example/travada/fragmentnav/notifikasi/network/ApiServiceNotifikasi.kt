package com.example.travada.fragmentnav.notifikasi.network

import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import com.example.travada.fragmentnav.notifikasi.pojo.GetDetailNotifikasiResponse
import com.example.travada.fragmentnav.notifikasi.pojo.GetNotifikasiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiServiceNotifikasi {
    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("notif")
    fun getNotifikasi(
        @Header("Authorization") token: String
    ): Call<GetNotifikasiResponse>

//    @GET("notif/detail/{id}")
//    fun getDetailNotifikasi(@Path("id")id: Int) : Call<GetDetailNotifikasiResponse>

    @GET("notif/detail/{id}")
    fun getDetailNotifikasi(@Header("Authorization") authHeader: String?, @Path("id")id:Int): Call<GetDetailNotifikasiResponse>
}