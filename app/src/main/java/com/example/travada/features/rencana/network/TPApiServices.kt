package com.example.travada.features.rencana.network

import com.example.travada.features.rencana.pojo.GetDestinasiAllResponse
import com.example.travada.features.rencana.pojo.GetDestinasiDetailResponse
import com.example.travada.features.rencana.pojo.*
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
    WISNU'S HERE */

    @GET("destinasi/populer")
    fun getPopulerDestination() : Call<GetPopulerResponse>

    @GET("destinasi/pilihan")
    fun getPilihanDestination() : Call<GetPilihanResponse>

    @GET("destinasi/{id}")
    fun getDestination(@Path("id") id: Int) : Call<GetDestinasiResponse>

    @GET("cicilan/{idDestinasi}/{jumlahOrang}?berangkat={tanggal}")
    fun getCicilan(@Path("idDestinasi") idDestinasi: Int,
                   @Path("jumlahOrang") jumlahOrang: Int,
                   @Path("tanggal") tanggal: String) : Call<GetCicilanResponse>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("pemesanan/base64/{idUser}")
    fun postPemesanan(
        @Header ("Authorization") token: String,
        @Path ("idUser") idUser: Int,
        @Body base64Request: PostPemesananBase64Request
    ): Call<PostPemesananResponse>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("pemesanan/destinasi/{idDestinasi}")
    fun getPemesananDestinasi(
        @Header ("Authorization") token: String,
        @Path ("idDestinasi") id: Int
    ) : Call<GetPemesananDestinasiResponse>

    @GET ("pemesanan/detail/{idPemesanan}")
    fun getPemesananDetail(
        @Path ("idPemesanan") idPemesanan: Int
    ) : Call<GetPemesananDetailResponse>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("auth/user/me")
    fun getUserInfo(@Header("Authorization") token: String) : Call<GetUserInfo>
}