package com.example.travada.features.transfer.transferbank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R

class TransferConfirmActivity : AppCompatActivity(), TransferConfirmPresenter.Listener {
    private lateinit var presenter:TransferConfirmPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_confirm)

        presenter = TransferConfirmPresenter(this)
    }
}