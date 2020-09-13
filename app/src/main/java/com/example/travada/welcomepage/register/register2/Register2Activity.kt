package com.example.travada.welcomepage.register.register2

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.welcomepage.register.register3.Register3Activity
import com.example.travada.welcomepage.register.takepicKTP.TakePicKTPActivity
import com.example.travada.welcomepage.register.takepicSelfieKTP.TakePicSelfieKTPActivity
import kotlinx.android.synthetic.main.activity_register1.*
import kotlinx.android.synthetic.main.activity_register2.*
import kotlinx.android.synthetic.main.activity_register2.btn_back
import kotlinx.android.synthetic.main.activity_register2.btn_next
import kotlinx.android.synthetic.main.activity_register2.statusbar


class Register2Activity : AppCompatActivity(), Register2Presenter.Listener {
    private lateinit var presenter: Register2Presenter
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        intent?.extras?.let { bundle = it }
        presenter = Register2Presenter(this)
        var cal = Calendar.getInstance()


        Glide
            .with(this)
            .load(R.drawable.image_register2)
            .into(statusbar)

        btn_KTP.setOnClickListener {
            presenter.goToTakePicKTPActivity()
        }

        btn_SelfieKTP.setOnClickListener {
            presenter.goToTakePicSelfieKTPActivity()
        }

        btn_next.setOnClickListener {
            presenter.goToNextPage(bundle)
        }

        btn_back.setOnClickListener {
            finish()
        }

        et_birth.setOnClickListener {
            presenter.setDatepicker(cal, et_birth.text.toString())
            presenter.checket(
                uriKTP,
                uriSelfieKTP,
                et_KTPnumb.text.toString(),
                et_KTPname.text.toString(),
                et_birth.text.toString(),
                gender
            )
        }

        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                gender = radio.text.toString()
                presenter.checket(
                    uriKTP,
                    uriSelfieKTP,
                    et_KTPnumb.text.toString(),
                    et_KTPname.text.toString(),
                    et_birth.text.toString(),
                    gender
                )
            })

        et_KTPname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checket(
                    uriKTP,
                    uriSelfieKTP,
                    et_KTPnumb.text.toString(),
                    et_KTPname.text.toString(),
                    et_birth.text.toString(),
                    gender
                )
            }
        })

        et_KTPnumb.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checket(
                    uriKTP,
                    uriSelfieKTP,
                    et_KTPnumb.text.toString(),
                    et_KTPname.text.toString(),
                    et_birth.text.toString(),
                    gender
                )
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == requestCodeKTP) {
            if (resultCode == Activity.RESULT_OK) {
                btn_KTP.setBackgroundResource(R.drawable.bg_btndone)
                btn_KTP.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_outline_camera_alt_24,
                    0,
                    R.drawable.ic_outline_check_circle_24,
                    0
                )
                data?.getStringExtra("result")?.let { uriKTP = it }
                presenter.checket(
                    uriKTP,
                    uriSelfieKTP,
                    et_KTPnumb.text.toString(),
                    et_KTPname.text.toString(),
                    et_birth.text.toString(),
                    gender
                )
            }
        } else if (requestCode == requestCodeSelfieKTP) {
            if (resultCode == Activity.RESULT_OK) {
                btn_SelfieKTP.setBackgroundResource(R.drawable.bg_btndone)
                btn_SelfieKTP.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_outline_camera_alt_24,
                    0,
                    R.drawable.ic_outline_check_circle_24,
                    0
                )
                data?.getStringExtra("result")?.let { uriSelfieKTP = it  }
                presenter.checket(
                    uriKTP,
                    uriSelfieKTP,
                    et_KTPnumb.text.toString(),
                    et_KTPname.text.toString(),
                    et_birth.text.toString(),
                    gender
                )
            }
        }

    }

    companion object {
        private const val requestCodeKTP = 801
        private const val requestCodeSelfieKTP = 802
        var uriKTP: String = ""
        var uriSelfieKTP: String = ""
        var gender: String = ""
    }

    override fun btnActive() {
        btn_next.setBackgroundResource(R.drawable.bg_btnactive)
        btn_next.setTextColor(Color.parseColor(getString(R.color.white)))
        btn_next.isClickable = true
    }

    override fun btnInactive() {
        btn_next.setBackgroundResource(R.drawable.bg_btninactive)
        btn_next.setTextColor(Color.parseColor(getString(R.color.gray)))
        btn_next.isClickable = false
    }

    override fun goToNextPage(bundle: Bundle) {
        val intent = Intent(this, Register3Activity::class.java)
        bundle.putString("uriKTP", uriKTP)
        bundle.putString("uriSelfieKTP", uriSelfieKTP)
        bundle.putString("KTPnumb", et_KTPnumb.text.toString())
        bundle.putString("KTPname", et_KTPname.text.toString())
        bundle.putString("birth", et_birth.text.toString())
        bundle.putString("gender", gender)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun goToTakePicKTPActivity() {
        val intent = Intent(this, TakePicKTPActivity::class.java)
        this.startActivityForResult(intent, requestCodeKTP)
    }

    override fun goToTakePicSelfieKTPActivity() {
        val intent = Intent(this, TakePicSelfieKTPActivity::class.java)
        this.startActivityForResult(intent, requestCodeSelfieKTP)
    }

    override fun btnBack() {
        finish()
    }


    override fun callDatepicker(cal: Calendar) {
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(android.icu.util.Calendar.YEAR, year)
                cal.set(android.icu.util.Calendar.MONTH, monthOfYear)
                cal.set(android.icu.util.Calendar.DAY_OF_MONTH, dayOfMonth)
                et_birth.setText(SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()))
            }
        }

        DatePickerDialog(
            this@Register2Activity,
            dateSetListener,
            cal.get(android.icu.util.Calendar.YEAR),
            cal.get(android.icu.util.Calendar.MONTH),
            cal.get(android.icu.util.Calendar.DAY_OF_MONTH)
        ).show()
    }

}