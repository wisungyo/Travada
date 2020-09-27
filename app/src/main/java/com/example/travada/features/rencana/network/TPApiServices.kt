package com.example.travada.features.rencana.network

import com.example.travada.features.rencana.pojo.GetDestinasiAllResponse
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import com.example.travada.features.rencana.pojo.*
import com.example.travada.welcomepage.pojo.PostResendRequest
import com.example.travada.welcomepage.pojo.PostResendResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.POST
import retrofit2.http.Path

interface TPApiServices {

    @GET("destinasi/pencarian")
    fun getSearchDestination(@Query("keyword") keyword: String?): Call<GetDestinasiAllResponse>

    @GET("destinasi/all")
    fun getalldestination() : Call<GetDestinasiAllResponse>

    @GET("destinasi/{id}")
    fun getDetailDestination(@Path("id")id: Int) : Call<GetDestinasiDetailResponse>

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

    @GET("destinasi/hargabenua")
    fun getFilteredDestination(@Query("termurah") termurah: String?, @Query("termahal") termahal: String?, @Query("benua") benua: String?): Call<GetDestinasiAllResponse>
}