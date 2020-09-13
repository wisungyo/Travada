package com.example.travada.welcomepage.loginpin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.numpad.NumPadClick
import com.example.numpad.numPadClickListener
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_login_pin.*


class LoginPinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_pin)

        Glide
            .with(this)
            .load(R.drawable.image_login)
            .into(iv_image)

        numpad.setOnNumPadClickListener(NumPadClick(numPadClickListener { nums:ArrayList<Int> ->
            val sb = StringBuilder()
            for (i in 0 until nums.size) {
                val num = nums[i]
                sb.append(num)
            }
            val result = sb.toString()
            PinView.setText(result)
        }))


    }
}