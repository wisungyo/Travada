package com.example.travada.features.topup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R

class TopUpInputActivity : AppCompatActivity(), TopUpInputPresenter.Listener {
    private lateinit var presenter: TopUpInputPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_input)
        presenter = TopUpInputPresenter(this)
    }
}