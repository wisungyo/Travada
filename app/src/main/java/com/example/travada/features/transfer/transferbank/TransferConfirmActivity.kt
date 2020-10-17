package com.example.travada.features.transfer.transferbank

import android.content.Intent
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_transfer_confirm.*
import java.util.*


class TransferConfirmActivity : AppCompatActivity(), TransferConfirmPresenter.Listener {
    private lateinit var presenter:TransferConfirmPresenter
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_confirm)

        presenter = TransferConfirmPresenter(this)
        intent?.extras?.let { bundle = it }

        setInfo(bundle)

        btn_back.setOnClickListener { finish() }

        btn_next.setOnClickListener {
            goToPinActivity(bundle)
        }
    }


    override fun goToPinActivity(bundle: Bundle) {
        val intent = Intent(this, TransferPinActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun setInfo(bundle: Bundle) {
        val nominal = bundle.getString("nominal")?.toLong()
        val accountnumb = bundle.getString("accountnumb").toString()
        val note = bundle.getString("note").toString()

        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)

        tv_accountnumb.text = StringBuilder(accountnumb).insert(4, ' ').insert(9, ' ').insert(14, ' ').toString()
        tv_note.text = note
        tv_transfer_nominal.text = numberFormat.format(nominal)
        tv_transfer_total.text = numberFormat.format(nominal)
    }
}