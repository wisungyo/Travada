package com.example.travada.welcomepage.network

import com.example.travada.welcomepage.pojo.PostLoginBody
import com.example.travada.welcomepage.pojo.PostLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    //Method Post
    @POST("auth/login")
    fun Login(@Body body: PostLoginBody) : Call<PostLoginResponse>
}