package com.example.travada.features.topup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_top_up_input_pin.*


class TopUpInputPinActivity : AppCompatActivity(), TopUpInputPinPresenter.Listener {
    private lateinit var presenter: TopUpInputPinPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_input_pin)
        presenter = TopUpInputPinPresenter(this)

        Glide
            .with(this)
            .load(R.drawable.ic_binar_big)
            .into(iv_image)

    }
}