package com.example.travada.features.tabungan.pojo


import com.google.gson.annotations.SerializedName

data class test(
    @SerializedName("foto")
    val foto: String,
    @SerializedName("tabungan")
    val tabungan: Tabungan,
    @SerializedName("userPrincipal")
    val userPrincipal: UserPrincipal
) {
    data class Tabungan(
        @SerializedName("autodebet")
        val autodebet: Boolean,
        @SerializedName("gambar_tabungan")
        val gambarTabungan: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("jumlah_setoran")
        val jumlahSetoran: Int,
        @SerializedName("jumlah_tabungan")
        val jumlahTabungan: Int,
        @SerializedName("nama")
        val nama: List<String>,
        @SerializedName("periode")
        val periode: String,
        @SerializedName("rekening")
        val rekening: List<String>,
        @SerializedName("setoran_awal")
        val setoranAwal: Int,
        @SerializedName("target")
        val target: String,
        @SerializedName("tujuan")
        val tujuan: String
    )

    data class UserPrincipal(
        @SerializedName("accountNonExpired")
        val accountNonExpired: Boolean,
        @SerializedName("accountNonLocked")
        val accountNonLocked: Boolean,
        @SerializedName("authorities")
        val authorities: List<Authority>,
        @SerializedName("credentialsNonExpired")
        val credentialsNonExpired: Boolean,
        @SerializedName("enabled")
        val enabled: Boolean,
        @SerializedName("id")
        val id: Int,
        @SerializedName("username")
        val username: String
    ) {
        data class Authority(
            @SerializedName("authority")
            val authority: String
        )
    }
}