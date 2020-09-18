package com.example.travada.features.rencana.detailrencana

import java.util.*

internal object DataInfoTambahan {
    val dataInfoTambahan: HashMap<String, List<String>>
        get() {
            val detailInfoTambahan = HashMap<String, List<String>>()

            val waktuDanCuaca: MutableList<String> = ArrayList()
            waktuDanCuaca.add("cuaca panas")


            val persiapanBerangkat: MutableList<String> = ArrayList()
            persiapanBerangkat.add("jam 10:00")


            val kesehatanDanKeamanan: MutableList<String> = ArrayList()
            kesehatanDanKeamanan.add("CIA and FBI")



            detailInfoTambahan["Waktu dan Cuaca"] = waktuDanCuaca
            detailInfoTambahan["Persiapan sebelum berangkat"] = persiapanBerangkat
            detailInfoTambahan["Kesehatan dan keamanan"] = kesehatanDanKeamanan

            return detailInfoTambahan
        }

}

