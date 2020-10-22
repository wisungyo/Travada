package com.example.travada.fragmentnav.akun.pojo


import com.google.gson.annotations.SerializedName

data class GetUserMeResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("accepted")
        val accepted: Boolean,
        @SerializedName("active")
        val active: Boolean,
        @SerializedName("balance")
        val balance: Int,
        @SerializedName("email")
        val email: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("nama_lengkap")
        val namaLengkap: String,
        @SerializedName("no_rekening")
        val noRekening: String,
        @SerializedName("pin")
        val pin: String
    )
}