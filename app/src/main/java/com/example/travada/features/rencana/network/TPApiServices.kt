package com.example.travada.features.rencana.network

import com.example.travada.features.rencana.pojo.*
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

    /*
    WISNU'S HERE
    ------------------------- */
    @GET("destinasi/populer")
    fun getPopulerDestination() : Call<GetPopulerResponse>

    @GET("destinasi/pilihan")
    fun getPilihanDestination() : Call<GetPilihanResponse>

    @GET("destinasi/{id}")
    fun getDestination(@Path("id") id: Int) : Call<GetDestinasiResponse>

    @GET("cicilan/{idDestinasi}/{jumlahOrang}")
    fun getCicilan(@Path("idDestinasi") idDestinasi: Int,
                   @Path("jumlahOrang") jumlahOrang: Int) : Call<GetCicilanResponse>
}