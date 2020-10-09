package com.example.travada.features.transfer.transferbank

import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_transfer_input.*
import java.util.*

class TransferInputActivity : AppCompatActivity(), TransferInputPresenter.Listener {
    private lateinit var presenter: TransferInputPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_input)

        presenter = TransferInputPresenter(this)

        et_transfer_nominal.addTextChangedListener(object : TextWatcher {
            var processed = ""

            override fun afterTextChanged(p0: Editable?) {
                val initial = p0.toString()
                if (et_transfer_nominal == null) return
                if (initial.isEmpty()) return

                val cleanString = initial.replace(".", "")
//                val cleanString1 = initial.replace("Rp ", "")
//                val localeID = Locale("in", "ID")
                val myFormatter = DecimalFormat("#.###")
//                val nf = myFormatter.format(cleanString)
                val nf = NumberFormat.getNumberInstance(Locale.GERMAN)
//                val nf = NumberFormat.getCurrencyInstance(localeID)
                nf.setGroupingUsed(true);

                var myNumber = cleanString.toLong()
                processed = nf.format(myNumber)
                et_transfer_nominal.removeTextChangedListener(this)
                et_transfer_nominal.setText(processed)
                Log.d("nan", "$p0")
                Log.d("nan", "${cleanString}")
                Log.d("nan", "${cleanString}")
                Log.d("nan", "----------------")

                try {
                    et_transfer_nominal.setSelection(processed.length)
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
                et_transfer_nominal.addTextChangedListener(this)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

}