package com.example.travada.features.tabungan.formtabungansatu

class FormTabunganOnePresenter(private val listener: Listener) {
    fun checked(tujuan: String, jumlah: String, uriGambar: String) {
        if (tujuan.isNotEmpty() && jumlah.isNotEmpty() && uriGambar.isNotEmpty()) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    fun checkTujuan(text: String){
        if(text.length<=25){
            listener.errTujuan(null)
            isError = false
        } else {
            listener.errTujuan("Tujuan minimal 25 karakter" )
            isError = true
        }
    }

    fun checkJumlah(jumlah: String){
        if(jumlah.length < 7){
            listener.errJumlah("minimal Rp.100.000")
            isError = true
        } else {
            listener.errJumlah(null)
            isError = false
        }
    }

    fun nextPage() {
        listener.goToNextPage()
    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun goToNextPage()
        fun errTujuan(message: String?)
        fun errJumlah(message: String?)
        fun goToUploadGambar()
        fun goToUpdateGambar()
    }

    companion object {
        var isError: Boolean = false
    }
}