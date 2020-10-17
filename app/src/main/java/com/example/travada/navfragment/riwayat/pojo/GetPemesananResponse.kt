package com.example.travada.navfragment.riwayat.pojo


import com.google.gson.annotations.SerializedName

data class GetPemesananResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String, // pengambilan list data pemesanan trip dengan id user 43 telah berhasil
    @SerializedName("status")
    val status: String // OK
) {
    data class Data(
        @SerializedName("id_destinasi")
        val idDestinasi: Int, // 40
        @SerializedName("id_pemesanan")
        val idPemesanan: Int, // 70
        @SerializedName("id_user")
        val idUser: Int, // 43
        @SerializedName("judul_trip")
        val judulTrip: String, // Maldives
        @SerializedName("nama_user")
        val namaUser: String, // yeppo
        @SerializedName("pemesanan")
        val pemesanan: Pemesanan,
        @SerializedName("status_disetujui")
        val statusDisetujui: String // Confirmed
    ) {
        data class Pemesanan(
            @SerializedName("berangkat")
            val berangkat: Any, // null
            @SerializedName("createdAt")
            val createdAt: String, // 2020-10-16T00:25:51.308+00:00
            @SerializedName("id")
            val id: Int, // 70
            @SerializedName("orang")
            val orang: Int, // 1
            @SerializedName("pulang")
            val pulang: Any, // null
            @SerializedName("status")
            val status: String, // Pending
            @SerializedName("total")
            val total: Int, // 13500000
            @SerializedName("updatedAt")
            val updatedAt: String // 2020-10-16T00:25:51.308+00:00
        )
    }
}