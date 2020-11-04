package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class GetPemesananDetailResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // pengambilan data pemesanan dengan id 31 berhasil dilakukan
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
            val id: Int, // 58
            @SerializedName("jatuh_tempo")
            val jatuhTempo: String, // 2020-10-29
            @SerializedName("jumlah")
            val jumlah: Int, // -4500000
            @SerializedName("status")
            val status: String // menunggu pembayaran
        )

        data class Pemesan(
            @SerializedName("email")
            val email: String, // wisnu1@mail.com
            @SerializedName("id")
            val id: Int, // 42
            @SerializedName("ktp")
            val ktp: String, // http://res.cloudinary.com/dxzkryz3x/image/upload/v1601407529/ksl2wp6q7k6net69ihyw.jpg
            @SerializedName("nama")
            val nama: String, // wisnu agung
            @SerializedName("no_hp")
            val noHp: String, // 1234567890
            @SerializedName("paspor")
            val paspor: String // http://res.cloudinary.com/dxzkryz3x/image/upload/v1601407530/n8cnn5xdjuo9mlethxuq.jpg
        )

        data class Pemesanan(
            @SerializedName("createdAt")
            val createdAt: String, // 2020-09-29T19:25:28.344+00:00
            @SerializedName("id")
            val id: Int, // 31
            @SerializedName("orang")
            val orang: Int, // 1
            @SerializedName("status")
            val status: String, // menunggu
            @SerializedName("total")
            val total: Int, // 13500000
            @SerializedName("updatedAt")
            val updatedAt: String // 2020-09-29T19:25:28.344+00:00
        )
    }
}