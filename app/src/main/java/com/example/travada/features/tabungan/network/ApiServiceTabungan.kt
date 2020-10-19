package com.example.travada.features.tabungan.network

import com.example.travada.features.tabungan.pojo.*
import com.example.travada.fragmentnav.akun.pojo.GetUserMeResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceTabungan {

    @POST("tabungan")
    fun createTabungan(
        @Body body: RequestBody, @Header("Authorization") token: String?): Call<PostTabunganResponse>


//    @POST("tabungan")
//    fun createTabungan(@Body body: RequestBody): Call<>

    @GET("auth/user/me")
    fun getMe(@Header("Authorization") authHeader: String?): Call<GetUserMeResponse>


    // GET
    @GET("tabungan/all")
    fun getAllTabungan(): Call<GetTabunganAll>

    @GET("tabungan/{id}")
    fun getDetailTabungan(@Path("id")id: Int) : Call<GetTabunganDetailResponse>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("tabungan/user/all")
    fun getTabunganUserAll(
        @Header("Authorization") token: String
    ): Call<GetTabunganUserAll>

    @GET("tabungan/teman?")
    fun getRekeningTeman(
        @Query("rekening") rekening: String
    ): Call<GetRekeningTemanResponse>

}