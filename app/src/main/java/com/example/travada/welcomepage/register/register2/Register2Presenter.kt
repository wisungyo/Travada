package com.example.travada.welcomepage.register.register2

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_register2.*
import java.util.*

class Register2Presenter(val listener: Listener) {

    fun checket(uriKTP: String, uriSelfieKTP: String, KTPnumb:String, KTPname:String, birth:String, gender:String) {
        if (uriKTP.isNotEmpty() && uriSelfieKTP.isNotEmpty() && KTPnumb.isNotEmpty() && KTPname.isNotEmpty() && birth.isNotEmpty() && gender.isNotEmpty()) {
            listener.btnActive()
        } else {
            listener.btnInactive()
        }
    }

    fun goToNextPage(bundle: Bundle) {
        listener.goToNextPage(bundle)
    }

    fun goToTakePicKTPActivity() {
        listener.goToTakePicKTPActivity()
    }

    fun goToTakePicSelfieKTPActivity() {
        listener.goToTakePicSelfieKTPActivity()
    }

    fun setDatepicker(cal:android.icu.util.Calendar, text:String) {
        if (text.isNotEmpty()) {
            val date = SimpleDateFormat("dd-MM-yyyy").parse(text)
            val year = SimpleDateFormat("yyyy").format(date).toInt()
            val month = SimpleDateFormat("MM").format(date).toInt() - 1
            val day = SimpleDateFormat("dd").format(date).toInt()
            cal.set(android.icu.util.Calendar.YEAR, year)
            cal.set(android.icu.util.Calendar.MONTH, month)
            cal.set(android.icu.util.Calendar.DAY_OF_MONTH, day)
        } else {
            cal.getTime()
        }
        listener.callDatepicker(cal)

    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun goToNextPage(bundle: Bundle)
        fun goToTakePicKTPActivity()
        fun goToTakePicSelfieKTPActivity()
        fun btnBack()
        fun callDatepicker(cal: Calendar)
    }
}