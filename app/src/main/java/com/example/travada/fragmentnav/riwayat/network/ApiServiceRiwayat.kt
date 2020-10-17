package com.example.travada.fragmentnav.riwayat.network

import com.example.travada.fragmentnav.riwayat.pojo.GetPemesananResponse
import com.example.travada.fragmentnav.riwayat.pojo.GetPemesananRiwayatResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiServiceRiwayat {

    @Headers ("Content-Type: application/json;charset=UTF-8")
    @GET("pemesanan")
    fun getPemesanan(
        @Header("Authorization") token: String
    ): Call<GetPemesananRiwayatResponse>

    @Headers ("Content-Type: application/json;charset=UTF-8")
    @GET("pemesanan")
    fun getPemesananNew(
        @Header("Authorization") token: String
    ): Call<GetPemesananResponse>
}