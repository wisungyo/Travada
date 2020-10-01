package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class GetPemesananDestinasiResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // pengambilan detail pemesanan dengan id user 5 dan id destinasi 98 berhasil dilakukan
    @SerializedName("status")
    val status: String // OK
) {
    data class Data(
        @SerializedName("cicilan")
        val cicilan: List<Cicilan>,
        @SerializedName("pemesan")
        val pemesan: List<Pemesan>,
        @SerializedName("pemesanan")
        val pemesanan: Pemesanan
    ) {
        data class Cicilan(
            @SerializedName("id")
            val id: Int, // 37
            @SerializedName("jatuh_tempo")
            val jatuhTempo: String, // 2020-10-28
            @SerializedName("jumlah")
            val jumlah: Int, // -260000
            @SerializedName("status")
            val status: String // menunggu pembayaran
        )

        data class Pemesan(
            @SerializedName("email")
            val email: String, // barbara@gmail.com
            @SerializedName("id")
            val id: Int, // 22
            @SerializedName("ktp")
            val ktp: String, // http://res.cloudinary.com/dxzkryz3x/image/upload/v1601281408/qbmnpesjrclaw8l2uadt.jpg
            @SerializedName("nama")
            val nama: String, // Frida Kahlo
            @SerializedName("no_hp")
            val noHp: String, // 0818181818
            @SerializedName("paspor")
            val paspor: String // http://res.cloudinary.com/dxzkryz3x/image/upload/v1601281410/yo1z3hfbw2bn0t0k6oft.jpg
        )

        data class Pemesanan(
            @SerializedName("createdAt")
            val createdAt: String, // 2020-09-28T08:23:27.229+00:00
            @SerializedName("id")
            val id: Int, // 18
            @SerializedName("orang")
            val orang: Int, // 1
            @SerializedName("status")
            val status: String, // disetujui
            @SerializedName("total")
            val total: Int, // 780000
            @SerializedName("updatedAt")
            val updatedAt: String // 2020-09-29T02:55:23.139+00:00
        )
    }
}