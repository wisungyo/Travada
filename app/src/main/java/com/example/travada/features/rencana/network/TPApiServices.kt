package com.example.travada.features.rencana.network

import com.example.travada.features.rencana.pojo.GetDestinasiAllResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TPApiServices {

    @GET("destinasi/pencarian")
    fun getSearchDestination(@Query("keyword") keyword: String?): Call<GetDestinasiAllResponse>

    @GET("destinasi/all")
    fun getalldestination() : Call<GetDestinasiAllResponse>

    @GET("destinasi/hargabenua")
    fun getFilteredDestination(@Query("termurah") termurah: String?, @Query("termahal") termahal: String?, @Query("benua") benua: String?): Call<GetDestinasiAllResponse>
}