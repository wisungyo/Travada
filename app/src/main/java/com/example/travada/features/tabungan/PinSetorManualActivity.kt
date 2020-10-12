package com.example.travada.features.tabungan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_login_pin.*

class PinSetorManualActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_setor_manual)

        Glide
            .with(this)
            .load(R.drawable.image_login)
            .into(iv_image)
    }
}