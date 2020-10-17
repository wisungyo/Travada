package com.example.travada.features.tabungan.network

import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import com.example.travada.features.tabungan.pojo.GetAllTabunganResponse
import com.example.travada.features.tabungan.pojo.GetTabunganDetailResponse
import com.example.travada.features.tabungan.pojo.PostTabunganBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceTabungan {

    // POST
    @POST("tabungan")
    fun createTabungan(@Body body: RequestBody): Call<PostTabunganBody>

    // GET
    @GET("tabungan/all")
    fun getAllTabungan(): Call<GetAllTabunganResponse>

    @GET("tabungan/{id}")
    fun getDetailTabungan(@Path("id")id: Int) : Call<GetTabunganDetailResponse>

}