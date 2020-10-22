package com.example.travada.features.transfer.va

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R

class VAConfirm1Activity : AppCompatActivity(), VAConfirm1Presenter.Listener {
    private lateinit var presenter: VAConfirm1Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_v_a_confirm1)

        presenter = VAConfirm1Presenter(this)
    }
}