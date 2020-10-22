package com.example.travada.features.transfer.va

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R

class VAInputActivity : AppCompatActivity(), VAInputPresenter.Listener {
    private lateinit var presenter:VAInputPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_v_a_input)
        presenter = VAInputPresenter(this)
    }
}