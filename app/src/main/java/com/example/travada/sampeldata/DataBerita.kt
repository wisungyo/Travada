package com.example.travada.sampeldata

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataBerita (val img: Int, val title: String, val subtitle: String, val date: String) : Parcelable