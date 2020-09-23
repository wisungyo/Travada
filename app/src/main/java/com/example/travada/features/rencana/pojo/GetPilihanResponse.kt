package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class GetPilihanResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String, // Pengambilan list data destinasi berdasarkan pilihan telah berhasil
    @SerializedName("status")
    val status: String // OK
) {
    data class Data(
        @SerializedName("benua")
        val benua: String, // Asia
        @SerializedName("berangkat")
        val berangkat: String, // 2020-12-12
        @SerializedName("createdAt")
        val createdAt: String, // 2020-09-18T05:45:14.502+00:00
        @SerializedName("deskripsi")
        val deskripsi: String, // Jakarta indah Jakarta menyenangkan Katanya
        @SerializedName("durasi")
        val durasi: Int, // 7
        @SerializedName("fasilitas")
        val fasilitas: List<String>,
        @SerializedName("gambar_list")
        val gambarList: List<String>,
        @SerializedName("harga_satuan")
        val hargaSatuan: Int, // 14500000
        @SerializedName("id")
        val id: Int, // 3
        @SerializedName("info_persiapan")
        val infoPersiapan: String, // bawa masker
        @SerializedName("info_waktu_cuaca")
        val infoWaktuCuaca: String, // panas-panas neraka
        @SerializedName("kapasitas")
        val kapasitas: Int, // 25
        @SerializedName("kapasitas_terisi")
        val kapasitasTerisi: Int, // 0
        @SerializedName("lokal")
        val lokal: Boolean, // false
        @SerializedName("nama_trip")
        val namaTrip: String, // Tokyo & Mount Fuji
        @SerializedName("overview")
        val overview: String, // Jalan ke Jakarta Cuy! Ada Covid
        @SerializedName("popularitas")
        val popularitas: Int, // 25
        @SerializedName("pulang")
        val pulang: String, // 2020-12-19
        @SerializedName("rencana_list")
        val rencanaList: List<String>,
        @SerializedName("syarat_ketentuan")
        val syaratKetentuan: Any, // null
        @SerializedName("updatedAt")
        val updatedAt: String // 2020-09-22T06:50:41.116+00:00
    )
}