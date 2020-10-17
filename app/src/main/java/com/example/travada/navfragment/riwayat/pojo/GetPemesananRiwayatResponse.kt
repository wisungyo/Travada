package com.example.travada.navfragment.riwayat.pojo


import com.google.gson.annotations.SerializedName

data class GetPemesananRiwayatResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String, // pengambilan list data pemesanan trip dengan id user 5 telah berhasil
    @SerializedName("status")
    val status: String // OK
) {
    data class Data(
        @SerializedName("id_destinasi")
        val idDestinasi: Int, // 15
        @SerializedName("id_user")
        val idUser: Int, // 5
        @SerializedName("judul_trip")
        val judulTrip: String, // Trip ke Australia Murah Banget Asli
        @SerializedName("nama_user")
        val namaUser: String, // travada
        @SerializedName("pemesanan")
        val pemesanan: Pemesanan
    ) {
        data class Pemesanan(
            @SerializedName("createdAt")
            val createdAt: String, // 2020-09-26T07:21:34.480+00:00
            @SerializedName("id")
            val id: Int, // 1
            @SerializedName("orang")
            val orang: Int, // 2
            @SerializedName("status")
            val status: String, // menunggu
            @SerializedName("total")
            val total: Int, // 1560000
            @SerializedName("updatedAt")
            val updatedAt: String // 2020-09-26T07:21:34.480+00:00
        )
    }
}