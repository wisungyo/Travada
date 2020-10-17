package com.example.travada.features.transfer.transferbank

import android.content.Intent
import android.graphics.Color
import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.travada.R
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_transfer_input.*
import java.util.*

class TransferInputActivity : AppCompatActivity(), TransferInputPresenter.Listener {
    private lateinit var presenter: TransferInputPresenter
    val MyFragment= LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_input)

        presenter = TransferInputPresenter(this)
        var myNumber = 0L
        presenter.setInformation()


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
                myNumber = cleanString.toLong()
                processed = nf.format(myNumber)
                et_transfer_nominal.removeTextChangedListener(this)
                et_transfer_nominal.setText(processed)

                try {
                    et_transfer_nominal.setSelection(processed.length)
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
                et_transfer_nominal.addTextChangedListener(this)

                presenter.checket(myNumber.toString(), tv_et_accountname.text.toString())
                presenter.checknominal(myNumber)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        tv_et_accountname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checket(myNumber.toString(), tv_et_accountname.text.toString())
            }

        })


        btn_next.setOnClickListener {
            goToNextPage(myNumber)
        }

        btn_back.setOnClickListener {
            finish()
        }

    }

    override fun btnActive() {
        btn_next.setBackgroundResource(R.drawable.bg_btnactive)
        btn_next.setTextColor(Color.parseColor(getString(R.color.white)))
        btn_next.isClickable = true
    }

    override fun btnInactive() {
        btn_next.setBackgroundResource(R.drawable.bg_btninactive)
        btn_next.setTextColor(Color.parseColor(getString(R.color.gray)))
        btn_next.isClickable = false
    }

    override fun goToNextPage(value: Long) {
        val intent = Intent(this, TransferConfirmActivity::class.java)
        val bundle = Bundle()
        var catatan = "-"
        if(et_note.text.toString().isEmpty()){
            catatan = "-"
        } else {
            catatan = et_note.text.toString()
        }
        bundle.putString("nominal", value.toString())
        bundle.putString("accountnumb", et_accountnumb.text.toString())
        bundle.putString("note", catatan)
        intent.putExtras(bundle)
        startActivity(intent)
    }


    override fun showLoadingDialog() {
        val fm=supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun errAccountnumb(text: String?) {
        til_accountnumb.error = text
    }

    override fun errTransfernominal(text: String?) {
        til_transfer_nominal.error = text
    }

    override fun showToast(text: String) {
        Toast.makeText(
            this@TransferInputActivity, text,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun setInfo(name: String) {
        tv_et_accountname.text = name
    }

    companion object {
        var isError = false
        var saldo = 0
    }

}