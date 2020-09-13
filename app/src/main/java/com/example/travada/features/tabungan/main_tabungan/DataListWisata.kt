package com.example.travada.features.tabungan.main_tabungan

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataListWisata(
    val namaWisata: String,
    val biaya: String,
    var tempo: String,
    val gambar: Int
):Parcelable {
}