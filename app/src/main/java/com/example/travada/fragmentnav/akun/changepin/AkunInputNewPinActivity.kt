package com.example.travada.fragmentnav.akun.changepin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R

class AkunInputNewPinActivity : AppCompatActivity(), AkunInputNewPinPresenter.Listener {
    private lateinit var presenter : AkunInputNewPinPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun_input_new_pin)

        presenter = AkunInputNewPinPresenter(this)
    }
}