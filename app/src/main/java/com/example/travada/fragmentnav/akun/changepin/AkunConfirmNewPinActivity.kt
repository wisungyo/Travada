package com.example.travada.fragmentnav.akun.changepin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R

class AkunConfirmNewPinActivity : AppCompatActivity(),AkunConfirmNewPinPresenter.Listener {
    private lateinit var presenter : AkunConfirmNewPinPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun_confirm_new_pin)

        presenter = AkunConfirmNewPinPresenter(this)


    }
}