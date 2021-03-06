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

    @GET("cicilan/{idDestinasi}/{jumlahOrang}?")
    fun getCicilan(@Path("idDestinasi") idDestinasi: Int,
                   @Path("jumlahOrang") jumlahOrang: Int,
                   @Query("berangkat") berangkat: String) : Call<GetCicilanResponse>

    @PUT("cicilan/{idCicilan}?")
    fun putCicilan(
        @Path("idCicilan") idCicilan: Int,
        @Query("status") status: String
    ): Call<PutCicilan>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @PUT("cicilan/bayar/{idCicilan}")
    fun putBayarCicilan(
        @Header ("Authorization") token: String,
        @Path("idCicilan") idCicilan: Int
    ): Call<PutBayarCicilan>

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

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/tabungan/user/all")
    fun getTabunganUserAll(
        @Header("Authorization") token: String
    ) : Call<GetTabunganUserAll>

    @GET("/nasabah/{idUser}")
    fun getNasabah(
        @Path("idUser") id: Int
    ) : Call<GetNasabah>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/banking/mutasi/filter/mingguan")
    fun getMutasiMingguan(
        @Header("Authorization") token: String
    ) : Call<GetMutasiFilter>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/banking/mutasi/filter/bulanan")
    fun getMutasiBulanan(
        @Header("Authorization") token: String
    ) : Call<GetMutasiFilter>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/banking/mutasi/filter/tahunan")
    fun getMutasiTahunan(
        @Header("Authorization") token: String
    ) : Call<GetMutasiFilter>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("di")
    fun getMutasiCustom(
        @Header("Authorization") token: String,
        @Query("awal") awal: String,
        @Query("akhir") akhir: String
    ) : Call<GetMutasiFilter>
}