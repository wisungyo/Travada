package com.example.travada.welcomepage.register.register1

import android.media.MediaCodec
import android.util.Patterns
import com.example.travada.welcomepage.register.register1.Register1Activity.Companion.isError
import com.example.travada.welcomepage.register.register2.Register2Presenter
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class Register1Presenter(val listener: Register1Presenter.Listener) {

    fun checket(name: String, email: String, username:String, phone:String, accountNumb:String) {
        if (name.isNotEmpty() && email.isNotEmpty() && username.isNotEmpty() && phone.isNotEmpty() && accountNumb.isNotEmpty() && isError == false) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    fun checkemail(text: String, view:TextInputLayout){
        if(Patterns.EMAIL_ADDRESS.matcher(text).matches()){
            listener.hideErrorMessage(view)
        } else {
            listener.showErrorMessage(view, "Format email tidak valid" )
        }
    }

    fun checkusername(text: String, view:TextInputLayout){
        if(text.length>=4){
            listener.hideErrorMessage(view)
        } else {
            listener.showErrorMessage(view, "Username minimal 4 karakter" )
        }
    }

    fun checkphone(text: String, view:TextInputLayout){
        if(text.length<=9){
            listener.showErrorMessage(view, "Nomer handphone minimal 10 karakter" )
        } else if (text.length>=12){
            listener.showErrorMessage(view, "Nomer handphone maksimal 13 karakter" )
        }else {
            listener.hideErrorMessage(view)
        }
    }

    fun goToNextPage() {
        listener.goToNextPage()
    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun goToNextPage()
        fun btnBack()
        fun showErrorMessage(layout: TextInputLayout, message: String)
        fun hideErrorMessage(layout: TextInputLayout)
    }
}