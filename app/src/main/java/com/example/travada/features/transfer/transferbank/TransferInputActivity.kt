package com.example.travada.features.transfer.transferbank

import android.content.Intent
import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.annotation.RequiresApi
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

            @RequiresApi(Build.VERSION_CODES.N)
            override fun afterTextChanged(p0: Editable?) {
                val initial = p0.toString()
                if (et_transfer_nominal == null) return
                if (initial.isEmpty()) return
                val cleanString = initial.replace(".", "")
                val nf = NumberFormat.getNumberInstance(Locale.GERMAN)
                nf.setGroupingUsed(true);
                val myNumber = cleanString.toLong()
                processed = nf.format(myNumber)
                et_transfer_nominal.removeTextChangedListener(this)
                et_transfer_nominal.setText(processed)

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

        btn_next.setOnClickListener {
            val goToNextActivity = Intent(this, TransferInvoiceActivity::class.java)
            startActivity(goToNextActivity)
        }

    }

    override fun btnActive() {
        TODO("Not yet implemented")
    }

    override fun btnInactive() {
        TODO("Not yet implemented")
    }

    override fun goToNextPage() {
        TODO("Not yet implemented")
    }

    override fun showLoadingDialog() {
        TODO("Not yet implemented")
    }

    override fun hideLoadingDialog() {
        TODO("Not yet implemented")
    }

    override fun errAccountnumb(text: String?) {
        TODO("Not yet implemented")
    }

    override fun errTransfernominal(text: String?) {
        TODO("Not yet implemented")
    }

    override fun showToast(text: String) {
        TODO("Not yet implemented")
    }

}