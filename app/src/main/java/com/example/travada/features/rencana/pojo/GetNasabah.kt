package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class GetNasabah(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // Data nasabah berhasil dimuat
    @SerializedName("status")
    val status: String // OK
) {
    data class Data(
        @SerializedName("active")
        val active: Boolean, // true
        @SerializedName("email")
        val email: String, // yeppo.cv@gmail.com
        @SerializedName("foto_ktp")
        val fotoKtp: String, // http://res.cloudinary.com/dxzkryz3x/image/upload/v1601734693/k2shrttelavhc1l0qxs8.jpg
        @SerializedName("id")
        val id: Int, // 43
        @SerializedName("jenis_kelamin")
        val jenisKelamin: String, // Pria
        @SerializedName("nama_lengkap")
        val namaLengkap: String, // Wisnu Agung Prasetyo
        @SerializedName("no_hp")
        val noHp: String, // 85632558588
        @SerializedName("no_ktp")
        val noKtp: String, // 1234567890000000
        @SerializedName("no_rekening")
        val noRekening: String, // 12345
        @SerializedName("selfie_ktp")
        val selfieKtp: String, // http://res.cloudinary.com/dxzkryz3x/image/upload/v1601734705/tisqdugpahddjfto1vgh.jpg
        @SerializedName("tags")
        val tags: List<String>,
        @SerializedName("tgl_lahir")
        val tglLahir: String, // 2020-10-03T00:00:00.000+00:00
        @SerializedName("username")
        val username: String // yeppo
    )
}