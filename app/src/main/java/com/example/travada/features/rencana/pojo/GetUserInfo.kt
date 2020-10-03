package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class GetUserInfo(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // Data user loaded
    @SerializedName("status")
    val status: String // OK
) {
    data class Data(
        @SerializedName("accepted")
        val accepted: Boolean, // false
        @SerializedName("active")
        val active: Boolean, // true
        @SerializedName("balance")
        val balance: Int, // 0
        @SerializedName("email")
        val email: String, // halotravada@gmail.com
        @SerializedName("id")
        val id: Int, // 5
        @SerializedName("nama_lengkap")
        val namaLengkap: String, // Travada Testing
        @SerializedName("no_rekening")
        val noRekening: String, // 085868794013
        @SerializedName("pin")
        val pin: String // Nzk0MDEz
    )
}