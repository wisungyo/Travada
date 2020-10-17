package com.example.travada.features.tabungan.network

import com.example.travada.features.tabungan.pojo.GetAllTabunganResponse
import com.example.travada.features.tabungan.pojo.GetTabunganDetailResponse
import com.example.travada.features.tabungan.pojo.GetRekeningTemanResponse
import com.example.travada.features.tabungan.pojo.PostTabunganResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceTabungan {
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("tabungan")
    fun createTabungan(
        @Header("Authorization") token: String,
        @Body body: RequestBody): Call<PostTabunganResponse>


//    @POST("tabungan")
//    fun createTabungan(@Body body: RequestBody): Call<>



    // GET
    @GET("tabungan/all")
    fun getAllTabungan(): Call<GetAllTabunganResponse>

    @GET("tabungan/{id}")
    fun getDetailTabungan(@Path("id")id: Int) : Call<GetTabunganDetailResponse>

    @GET("tabungan/teman?")
    fun getRekeningTeman(
        @Query("rekening") rekening: String
    ): Call<GetRekeningTemanResponse>

}