package com.example.travada.features.mutasi.presenter

import android.R
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetNasabah
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Response
import java.util.*


class MutasiActivityPresenter(val listener: Listener): AppCompatActivity() {

    fun fetchData() {
        TPApiClient.TP_API_SERVICES.getNasabah(Hawk.get(util.SF_ID, 0)).enqueue(object : retrofit2.Callback<GetNasabah> {
            override fun onResponse(call: Call<GetNasabah>, response: Response<GetNasabah>) {
                if (!response.isSuccessful) {
//                    listener.showDataError("Fetching data gagal")
                    return
                }
                response.body()?.data?.let { listener.showUserData(it) }
            }

            override fun onFailure(call: Call<GetNasabah>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }

    fun goToResultMutasi() {
        listener.showResultMutasi()
    }

    fun getTheDate(context: Context, source: String, date: String) {
        /*
        * SHOW DATEPICKER WHEN DATE IS NOT SET BEFORE */
        if (date == "Dari Tanggal" || date == "Sampai Tanggal") {
            val cal: Calendar   = Calendar.getInstance()
            val year: Int       = cal.get(Calendar.YEAR)
            val month: Int      = cal.get(Calendar.MONTH)
            val day: Int        = cal.get(Calendar.DAY_OF_MONTH)

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
            dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "Atur", dialog)
            dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Batal", null as DialogInterface.OnClickListener?)
            dialog.datePicker.maxDate = Date().time
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
            orderDate(dialog, charArrayOf('d', 'm', 'y'))
        } else {
            /*
            * SHOW DATEPICKER WHEN DATE IS ALREADY SET BEFORE */
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

            val cal: Calendar   = Calendar.getInstance(Locale.ITALY)
            cal.set(splitDate[2].toInt(), splitDate[1].toInt(), splitDate[0].toInt())
            val year: Int       = cal.get(Calendar.YEAR)
            val month: Int      = cal.get(Calendar.MONTH)
            val day: Int        = cal.get(Calendar.DAY_OF_MONTH)

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
            dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "Atur", dialog)
            dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Batal") { _: DialogInterface, _: Int ->
                dialog.dismiss()
                listener.checkNextButton()
            }
            dialog.datePicker.maxDate = Date().time
//            if (source == "start") {
//                dialog.datePicker.maxDate =
//            } else {
//
//            }
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
            orderDate(dialog, charArrayOf('d', 'm', 'y'))
        }
    }

    private val SPINNER_COUNT = 3
    private fun orderDate(dialog: DatePickerDialog, ymdOrder: CharArray) {
        check(dialog.isShowing) { "Dialog must be showing" }
        val idYear              = Resources.getSystem().getIdentifier("year", "id", "android")
        val idMonth             = Resources.getSystem().getIdentifier("month", "id", "android")
        val idDay               = Resources.getSystem().getIdentifier("day", "id", "android")
        val idLayout            = Resources.getSystem().getIdentifier("pickers", "id", "android")
        val spinnerYear = dialog.findViewById<View>(idYear) as NumberPicker
        val spinnerMonth= dialog.findViewById<View>(idMonth) as NumberPicker
        val spinnerDay  = dialog.findViewById<View>(idDay) as NumberPicker
        val layout       = dialog.findViewById<View>(idLayout) as LinearLayout
        layout.removeAllViews()
        for (i in 0 until SPINNER_COUNT) {
            when (ymdOrder[i]) {
                'y' -> {
                    layout.addView(spinnerYear)
                    setImeOptions(spinnerYear, i)
                }
                'm' -> {
                    layout.addView(spinnerMonth)
                    setImeOptions(spinnerMonth, i)
                }
                'd' -> {
                    layout.addView(spinnerDay)
                    setImeOptions(spinnerDay, i)
                }
                else -> throw IllegalArgumentException("Invalid char[] ymdOrder")
            }
        }
    }

    private fun setImeOptions(spinner: NumberPicker, spinnerIndex: Int) {
        val imeOptions: Int =
            if (spinnerIndex < SPINNER_COUNT - 1) {
                EditorInfo.IME_ACTION_NEXT
            } else {
                EditorInfo.IME_ACTION_DONE
            }
        val idPickerInput =
            Resources.getSystem().getIdentifier("numberpicker_input", "id", "android")
        val input = spinner.findViewById<View>(idPickerInput) as TextView
        input.imeOptions   = imeOptions
    }

    /*
    * LISTENERS */
    interface Listener {
        fun showResultMutasi()
        fun setTheDate(year: Int, month: Int, day: Int, source: String)
        fun checkNextButton()
        fun getContext(): Context
        fun showUserData(data: GetNasabah.Data)
    }
}