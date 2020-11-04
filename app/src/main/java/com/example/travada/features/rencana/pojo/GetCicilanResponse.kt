package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class GetCicilanResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String, // sukses
    @SerializedName("status")
    val status: String // CREATED
) {
    data class Data(
        @SerializedName("id")
        val id: Any, // null
        @SerializedName("jatuh_tempo")
        val jatuhTempo: String, // 2020-10-25
        @SerializedName("jumlah")
        val jumlah: Int, // -520000
        @SerializedName("status")
        val status: String // menunggu pembayaran
    )
}