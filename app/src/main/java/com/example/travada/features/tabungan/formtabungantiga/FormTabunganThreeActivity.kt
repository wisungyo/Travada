package com.example.travada.features.tabungan.formtabungantiga

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.travada.R
import com.example.travada.features.tabungan.dialogfragment.PeriksaRekeningDialog
import com.example.travada.features.tabungan.formtabungandua.FormTabunganTwoActivity
import com.example.travada.features.tabungan.pojo.GetRekeningTemanResponse
import kotlinx.android.synthetic.main.activity_form_tabungan_three.*

class FormTabunganThreeActivity : AppCompatActivity(),
    FormTabunganThreePresenter.Listener {

    lateinit var namaRekeningTeman : String
    lateinit var nomorRekeningTeman : String

    private lateinit var presenter: FormTabunganThreePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_tabungan_three)

        btn_check.setOnClickListener {
            presenter.getRekeningTeman(etNomorRekening.text.toString())
        }

        ivFormThreeBack.setOnClickListener {
          finish()
        }

        btnPeriksa.setOnClickListener {
//            Log.d("Main", "button pressed")
//            val fm  = supportFragmentManager
//            PeriksaRekeningDialog().show(fm, "AddMemo Fragment")
            goToTerima()
        }

        presenter = FormTabunganThreePresenter(this)

        etNomorRekening.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(etNomorRekening.text.toString())
            }
        })
    }

    override fun goToTerima() {
        val returnIntent = Intent()
        returnIntent.putExtra(namaRekening,  namaRekeningTeman)
        returnIntent.putExtra(nomorRekening,  nomorRekeningTeman)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }


    override fun implementLihatRekening(getRekeningTeman: GetRekeningTemanResponse) {
        tvNamaTeman.visibility = View.VISIBLE
        tvRekenigTeman.visibility = View.VISIBLE
        tvNamaTeman.text = getRekeningTeman.nama
        tvRekenigTeman.text = getRekeningTeman.rekening

        namaRekeningTeman = getRekeningTeman.nama
        nomorRekeningTeman = getRekeningTeman.rekening
    }

    override fun implementLihatRekeningFailure(error: String) {
        TODO("Not yet implemented")
    }

    override fun btnActive() {
        btnPeriksa.setBackgroundResource(R.drawable.bg_active)
        btnPeriksa.setTextColor(Color.parseColor("#ffffff"))
        btnPeriksa.setElevation(2f)
        btnPeriksa.isClickable = true

//        btnLanjutForm2.setOnClickListener {
//            val goToFormTabunganThree = Intent(this, FormTabunganActivityThree::class.java)
//            startActivity(goToFormTabunganThree)
//        }
    }

    override fun btnInactive() {
        btnPeriksa.setBackgroundResource(R.drawable.bg_inactive)
        btnPeriksa.isClickable = false
    }

    companion object {
        val namaRekening = "NAMA_REKENING"
        val nomorRekening = "NOMOR_REKENING"
    }
}