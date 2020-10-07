package com.example.travada.features.mutasi.presenter

import android.R
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MutasiActivityPresenter(val listener: Listener): AppCompatActivity() {

    fun goToResultMutasi() {
        listener.showResultMutasi()
    }

    fun getTheDate(context: Context, source: String, date: String) {
        if (date == "Dari Tanggal" || date == "Sampai Tanggal") {
            val cal: Calendar = Calendar.getInstance()
            val year: Int = cal.get(Calendar.YEAR)
            val month: Int = cal.get(Calendar.MONTH)
            val day: Int = cal.get(Calendar.DAY_OF_MONTH)

            val dialog =
                DatePickerDialog(
                    context,
                    R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                    { _, year, month, day ->
                        if (source == "start") {
                            listener.setTheDate(year, month, day, "start")
                        } else {
                            listener.setTheDate(year, month, day, "end")
                        }
                    },
                    year, month, day
                )
            dialog.datePicker.maxDate = Date().time
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        } else {
            val splitDate = date.split(" ").toMutableList()
            splitDate[1] =
                when (splitDate[1]) {
                "Jan" -> "0"
                "Feb" -> "1"
                "Mar" -> "2"
                "Apr" -> "3"
                "Mei" -> "4"
                "Jun" -> "5"
                "Jul" -> "6"
                "Agu" -> "7"
                "Sep" -> "8"
                "Okt" -> "9"
                "Nov" -> "10"
                "Des" -> "11"
                else  -> ""
            }

            val cal: Calendar = Calendar.getInstance(Locale.ITALY)
            cal.set(splitDate[2].toInt(), splitDate[1].toInt(), splitDate[0].toInt())
            val year: Int = cal.get(Calendar.YEAR)
            val month: Int = cal.get(Calendar.MONTH)
            val day: Int = cal.get(Calendar.DAY_OF_MONTH)

            val dialog =
                DatePickerDialog(
                    context,
                    R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                    { _, year, month, day ->
                        if (source == "start") {
                            listener.setTheDate(year, month, day, "start")
                        } else {
                            listener.setTheDate(year, month, day, "end")
                        }
                    },
                    year, month, day
                )
            dialog.datePicker.maxDate = Date().time
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }
    }

    interface Listener {
        fun showResultMutasi()
        fun setTheDate(year: Int, month: Int, day: Int, source: String)
    }
}