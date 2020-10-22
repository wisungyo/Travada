package com.example.travada.features.topup.network

import com.example.travada.fragmentnav.akun.network.AkunApiServices
import com.example.travada.util.util
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TopUpApiClient {
    private const val BASE_URL = util.BASE_URL

    private val client = OkHttpClient.Builder()
        .connectTimeout(90, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(90, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(90, java.util.concurrent.TimeUnit.SECONDS)
        .build()

    val TOPUP_API_SERVICES : TopUpApiServices by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(TopUpApiServices::class.java)
    }
}