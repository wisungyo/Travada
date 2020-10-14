package com.example.travada.features.tabungan.detailtabungan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_top_up.*

class TopUpTabunganActivity : AppCompatActivity(), TopUpTabunganPresenter.Listener {
    lateinit var presenter : TopUpTabunganPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up)

        presenter = TopUpTabunganPresenter(this)

        btn_back.setOnClickListener {
            finish()
        }
    }
}