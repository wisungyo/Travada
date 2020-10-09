package com.example.travada.features.transfer.va

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R

class VAConfirm2Activity : AppCompatActivity(), VAConfirm2Presenter.Listener {
    private lateinit var presenter:VAConfirm2Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_v_a_confirm2)
        presenter = VAConfirm2Presenter(this)
    }
}