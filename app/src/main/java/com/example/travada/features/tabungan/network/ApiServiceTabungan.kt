package com.example.travada.features.tabungan.network

import com.example.travada.features.tabungan.pojo.GetAllTabunganResponse
import com.example.travada.features.tabungan.pojo.PostTabunganBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceTabungan {

    // POST
    @POST("tabungan")
    fun reateTabungan1(@Body body: RequestBody): Call<PostTabunganBody>

    // GET
    @GET("tabungan/all")
    fun getAllTabungan(): Call<GetAllTabunganResponse>

}