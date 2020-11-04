package com.example.travada.welcomepage.pojo


import com.google.gson.annotations.SerializedName

data class PostCheckRegister1Request(
    @SerializedName("email")
    val email: String,
    @SerializedName("no_hp")
    val noHp: String,
    @SerializedName("no_rekening")
    val noRekening: String,
    @SerializedName("username")
    val username: String
)