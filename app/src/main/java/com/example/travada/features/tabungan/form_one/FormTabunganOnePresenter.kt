package com.example.travada.features.tabungan.form_one

class FormTabunganOnePresenter(private val listener: Listener)  {

    fun checked(tujuan: String, jumlah: String) {
        if (tujuan.isNotEmpty() && jumlah.isNotEmpty()) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }



//
//    fun checkTujuan(tujuan: String){
//        if (tujuan.length > 25 ){
//            tujuan.error("kelebihan kata")
//        } else{
//            tujuan.error = null
//        }
//    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
    }
}