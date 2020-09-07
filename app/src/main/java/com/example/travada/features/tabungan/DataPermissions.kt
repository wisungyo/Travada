package com.example.travada.features.tabungan

import android.Manifest

class DataPermissions {

    companion object{
        //REQUEST_CODE untuk meminta banyak permission yang terdiri dari CAMERA, WRITE EXT STORAGE, dan READ EXT STORAGE
        const val REQUEST_CODE = 201

        //Digunakan jika user memilih Ambil gambar dari Camera
        const val CAMERA_REQUEST = 1001

        //RequestDigunakan jika user memilih Pilih Gambar dari Gallery
        const val GALLERY_REQUEST = 1002

        //Daftar permission yang akan dipinta
        val arrayListPermission = arrayOf(
            //Agar aplikasi bisa membuka aplikasi Camera
            Manifest.permission.CAMERA,
            //Agar hasil dari tangkapan Aplikasi Camera bisa ditulis/disimpan ke Storage
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            //Agar bisa membaca hasil tangkapan camera maupun membaca gambar yang sudah ada di Gallery HP
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }
}