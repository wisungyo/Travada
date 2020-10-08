package com.example.travada.fragmentnav.notifikasi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DataNotifikasi(
    val judul: String,
    val pesan: String,
    val kategori :String,
    var tanggal: String ) : Parcelable