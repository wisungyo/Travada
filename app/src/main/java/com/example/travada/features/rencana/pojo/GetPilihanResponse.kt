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
        val berangkat: List<Any>,
        @SerializedName("createdAt")
        val createdAt: String, // 2020-09-28T05:59:54.285+00:00
        @SerializedName("deskripsi")
        val deskripsi: String, // Hsjsjsjsnvsmsk
        @SerializedName("durasi")
        val durasi: Int, // 4
        @SerializedName("fasilitas")
        val fasilitas: List<String>,
        @SerializedName("gambar_list")
        val gambarList: List<String>,
        @SerializedName("harga_satuan")
        val hargaSatuan: Int, // 10000000
        @SerializedName("id")
        val id: Int, // 159
        @SerializedName("info_persiapan")
        val infoPersiapan: String, // Mental anda
        @SerializedName("info_waktu_cuaca")
        val infoWaktuCuaca: String, // 1 jam lebih cepat dari jakarta
        @SerializedName("kapasitas")
        val kapasitas: Int, // 25
        @SerializedName("kapasitas_terisi")
        val kapasitasTerisi: Int, // 1
        @SerializedName("lokal")
        val lokal: Boolean, // true
        @SerializedName("nama_trip")
        val namaTrip: String, // Labuan Bajo
        @SerializedName("overview")
        val overview: String, // Lorem ipsumhsjsjbavaksn 
        @SerializedName("popularitas")
        val popularitas: Int, // 85
        @SerializedName("pulang")
        val pulang: List<Any>,
        @SerializedName("rencana_list")
        val rencanaList: List<String>,
        @SerializedName("syarat_ketentuan")
        val syaratKetentuan: String, // No refund
        @SerializedName("updatedAt")
        val updatedAt: String // 2020-10-14T11:00:01.166+00:00
    )
}