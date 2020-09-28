package com.example.travada.features.rencana.network

import com.example.travada.features.rencana.pojo.GetDestinasiAllResponse
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import com.example.travada.features.rencana.pojo.*
import com.example.travada.welcomepage.pojo.PostResendRequest
import com.example.travada.welcomepage.pojo.PostResendResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface TPApiServices {

    @GET("destinasi/pencarian")
    fun getSearchDestination(@Query("keyword") keyword: String?): Call<GetDestinasiAllResponse>

    @GET("destinasi/all")
    fun getalldestination() : Call<GetDestinasiAllResponse>

    @GET("destinasi/{id}")
    fun getDetailDestination(@Path("id")id: Int) : Call<GetDestinasiDetailResponse>

    @GET("destinasi/hargabenua")
    fun getFilteredDestination(@Query("termurah") termurah: String?, @Query("termahal") termahal: String?, @Query("benua") benua: String?): Call<GetDestinasiAllResponse>

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

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("pemesanan")
    fun postPemesanan(
        @Header ("Authorization") token: String,
        @Body body: RequestBody): Call<PostPemesananResponse>
}