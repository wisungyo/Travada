package com.example.travada.fragmentnav.notifikasi.pojo


import com.google.gson.annotations.SerializedName

data class GetNotifikasiResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("jenis")
        val jenis: String,
        @SerializedName("judul")
        val judul: String,
        @SerializedName("pesan")
        val pesan: String,
        @SerializedName("terbaca")
        val terbaca: Boolean,
        @SerializedName("updatedAt")
        val updatedAt: String
    )
}