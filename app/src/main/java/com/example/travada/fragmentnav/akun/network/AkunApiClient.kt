package com.example.travada.fragmentnav.akun.network

import com.example.travada.util.util
import com.example.travada.welcomepage.network.WPApiClient
import com.example.travada.welcomepage.network.WPApiServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AkunApiClient {
    private const val BASE_URL = util.BASE_URL

    private val client = OkHttpClient.Builder()
        .connectTimeout(90, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(90, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(90, java.util.concurrent.TimeUnit.SECONDS)
        .build()

    val AKUN_API_SERVICES : AkunApiServices by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(AkunApiServices::class.java)
    }
}