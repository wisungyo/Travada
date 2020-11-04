package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class PostPemesananResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // pemesanan telah dibuat
    @SerializedName("status")
    val status: String // CREATED
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
            val id: Int, // 15
            @SerializedName("jatuh_tempo")
            val jatuhTempo: String, // 2020-10-27
            @SerializedName("jumlah")
            val jumlah: Int, // -520000
            @SerializedName("status")
            val status: String // menunggu pembayaran
        )

        data class Pemesan(
            @SerializedName("email")
            val email: String, // barbara@gmail.com
            @SerializedName("id")
            val id: Int, // 11
            @SerializedName("ktp")
            val ktp: String, // http://res.cloudinary.com/dxzkryz3x/image/upload/v1601217979/abbba7buq7ommrx9rniw.jpg
            @SerializedName("nama")
            val nama: String, // Barbara
            @SerializedName("no_hp")
            val noHp: String, // 0818181818
            @SerializedName("paspor")
            val paspor: String // http://res.cloudinary.com/dxzkryz3x/image/upload/v1601217981/ucvkwndfxyndjdfgrdc0.jpg
        )

        data class Pemesanan(
            @SerializedName("createdAt")
            val createdAt: String, // 2020-09-27T14:46:18.713+00:00
            @SerializedName("id")
            val id: Int, // 9
            @SerializedName("orang")
            val orang: Int, // 2
            @SerializedName("status")
            val status: String, // menunggu
            @SerializedName("total")
            val total: Int, // 1560000
            @SerializedName("updatedAt")
            val updatedAt: String // 2020-09-27T14:46:18.713+00:00
        )
    }
}