package com.example.travada.welcomepage.forgetpass.inputemail

import android.os.Bundle
import android.util.Patterns

class ForgetpassInputEmailPresenter(val listener:Listener) {

    fun checket(email:String) {
        if (email.isNotEmpty() && ForgetpassInputEmailActivity.isError == false) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    fun checkEmail(text: String){
        if(Patterns.EMAIL_ADDRESS.matcher(text).matches()){
            listener.hideErrorMessage()
            ForgetpassInputEmailActivity.isError = false
        } else {
            listener.ShowErrorMessage("Format email tidak valid" )
            ForgetpassInputEmailActivity.isError = true
        }
    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun ShowErrorMessage(text: String?)
        fun hideErrorMessage()
        fun goToNextPage(bundle: Bundle)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showToast(text: String)
    }
}