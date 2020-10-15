package com.example.travada.features.topup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R

class TopUpInvoiceActivity : AppCompatActivity(), TopUpInvoicePresenter.Listener {
    private lateinit var presenter: TopUpInvoicePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_invoice)
        presenter = TopUpInvoicePresenter(this)

    }
}