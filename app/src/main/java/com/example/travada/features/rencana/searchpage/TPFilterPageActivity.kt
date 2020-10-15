package com.example.travada.features.rencana.searchpage

import android.app.Activity
import android.content.Intent
import android.icu.text.NumberFormat
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener
import com.example.travada.R
import com.example.travada.welcomepage.register.takepicKTP.TakePicKTPActivity
import kotlinx.android.synthetic.main.activity_t_p_filter_page.*
import kotlinx.android.synthetic.main.item_t_p_search_page.view.*
import java.util.*
import kotlin.collections.ArrayList


class TPFilterPageActivity : AppCompatActivity(), TPFilterPagePresenter.Listener {
    private lateinit var presenter: TPFilterPagePresenter
    var filterValueMin:Int = 0
    var filterValueMax:Int = 50000000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_t_p_filter_page)


        sb_price.setOnRangeSeekbarChangeListener({ minValue, maxValue ->
            onValueChange(minValue, maxValue)
        })

        btn_back.setOnClickListener { finish() }

        btn_next.setOnClickListener {
            onFilter()

        }

    }

    override fun onValueChange(minValue: Number, maxValue: Number) {
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        sb_valueMin.setText(numberFormat.format(minValue.toInt()*10000))
        sb_valueMax.setText(numberFormat.format(maxValue.toInt()*10000))
        filterValueMin = minValue.toInt()*10000
        filterValueMax = maxValue.toInt()*10000
    }

    override fun onFilter() {
        var listBenua = ArrayList<String>()
        if (Asia.isChecked) {
            listBenua.add("Asia")
        }
        if (Amerika.isChecked) {
            listBenua.add("Amerika")
        }
        if (Australia.isChecked) {
            listBenua.add("Australia")
        }
        if (Afrika.isChecked) {
            listBenua.add("Afrika")
        }
        if (Eropa.isChecked) {
            listBenua.add("Eropa")
        }

        var result =  listBenua.toString().substringAfter("[").substringBefore(']')

        if (result.isEmpty()){
            result = "Asia,Amerika,Australia,Afrika,Eropa"
        }

        val returnIntent = Intent()
        returnIntent.putExtra("benua", result)
        returnIntent.putExtra("maxFilter", filterValueMax)
        returnIntent.putExtra("minFilter", filterValueMin)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()

    }

}