package com.example.travada.features.transfer.transferbank.network


import com.example.travada.features.transfer.transferbank.pojo.GetNameResponse
import com.example.travada.features.transfer.transferbank.pojo.GetUserMeResponse
import com.example.travada.features.transfer.transferbank.pojo.PostTransferRequest
import com.example.travada.features.transfer.transferbank.pojo.PostTransferResponse
import retrofit2.Call
import retrofit2.http.*

interface TransferApiServices {

    @POST("banking/transfertravada")
    fun Transfer(@Header("Authorization") authHeader: String?, @Body body: PostTransferRequest): Call<PostTransferResponse>

    @GET("auth/user/me")
    fun getMe(@Header("Authorization") authHeader: String?): Call<GetUserMeResponse>

    @GET("tabungan/teman")
    fun putCicilan(
        @Query("rekening") rekening: String?
    ): Call<GetNameResponse>
}