package com.example.travada.features.rencana.network

import com.example.travada.features.rencana.pojo.GetDestinasiAllResponse
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import com.example.travada.welcomepage.pojo.PostResendRequest
import com.example.travada.welcomepage.pojo.PostResendResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TPApiServices {

    @GET("destinasi/all")
    fun getalldestination() : Call<GetDestinasiAllResponse>

    @GET("destinasi/{id}")
    fun getDetailDestination(@Path("id")id: Int) : Call<GetDestinasiDetailResponse>
}