package com.example.travada.fragmentnav.akun.changepass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R

class AkunChangePassActivity : AppCompatActivity(), AkunChangePassPresenter.Listener {
    private lateinit var presenter : AkunChangePassPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun_change_pass)

        presenter = AkunChangePassPresenter(this)

    }
}