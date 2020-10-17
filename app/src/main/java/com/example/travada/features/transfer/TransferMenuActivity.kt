package com.example.travada.features.transfer

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.travada.R
import com.example.travada.features.transfer.transferbank.TransferInputActivity
import com.example.travada.features.transfer.transferbank.TransferInvoiceActivity
import com.example.travada.features.transfer.va.VAInputActivity
import com.example.travada.features.transfer.va.VAInvoiceActivity
import kotlinx.android.synthetic.main.activity_transfer_menu.*

class TransferMenuActivity : AppCompatActivity(), TransferMenuPresenter.Listener {
    private lateinit var presenter: TransferMenuPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_menu)
        presenter = TransferMenuPresenter(this)

        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { _, _ ->
                btnActive()
            })

        btn_back.setOnClickListener {
            finish()
        }

        btn_next.setOnClickListener {
            var id: Int = radioGroup.checkedRadioButtonId

            if (id!=-1){
                val radio:RadioButton = findViewById(id)
                if (radio.text == "Transfer Bank") {
                    goToTransferActivity()
                } else {
                    goToVAActivity()
                }
            }else{
                showToast("Tidak ada yang dipilih")
            }
        }

        btnInactive()
    }

    override fun goToTransferActivity() {
        val goToNextActivity = Intent(this, TransferInputActivity::class.java)
//        val goToNextActivity = Intent(this, TransferInvoiceActivity::class.java)
        startActivity(goToNextActivity)
    }

    override fun goToVAActivity() {
//        val goToNextActivity = Intent(this, VAInputActivity::class.java)
////        val goToNextActivity = Intent(this, VAInvoiceActivity::class.java)
//        startActivity(goToNextActivity)
        Toast.makeText(
            this,
            "Under construction..",
            Toast.LENGTH_SHORT
        ).show()
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

    override fun showToast(text: String) {
        Toast.makeText(
            this@TransferMenuActivity, text,
            Toast.LENGTH_LONG
        ).show()
    }
}