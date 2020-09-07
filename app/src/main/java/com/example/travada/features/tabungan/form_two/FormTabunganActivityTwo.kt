package com.example.travada.features.tabungan.form_two

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.travada.R
import com.example.travada.features.tabungan.CalendarHelper
import com.example.travada.features.tabungan.form_three.FormTabunganActivityThree
import com.example.travada.features.tabungan.form_one.FormTabunganActivityOne
import kotlinx.android.synthetic.main.activity_form_tabungan_one.*
import kotlinx.android.synthetic.main.activity_form_tabungan_two.*
import kotlinx.android.synthetic.main.activity_form_tabungan_two.btnBack
import java.util.*

class FormTabunganActivityTwo : AppCompatActivity(),
    FormTabunganTwoPresenter.Listener {
    lateinit var DateEditText: EditText
    private lateinit var presenter: FormTabunganTwoPresenter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_tabungan_two)

        btnTambahTeman.setOnClickListener {
            val goToFormTambahTeman = Intent(this, FormTabunganActivityThree::class.java)
            startActivity(goToFormTambahTeman)
        }

        DateEditText = findViewById(R.id.etTanggal)
        showDatePicker()

        btnBack.setOnClickListener {
            val backToTabungan = Intent(this, FormTabunganActivityOne::class.java)
            startActivity(backToTabungan)
        }

        var metodeTabungan = etMetodeTabungan as AutoCompleteTextView
        fun dataMetodeTabungan() {
            var list = listOf("Auto debet", "Manual")
            var adapter =
                ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list)
            metodeTabungan.setAdapter(adapter)
            metodeTabungan.threshold = 1
        }

        metodeTabungan.setOnClickListener {
            dataMetodeTabungan()
            metodeTabungan.showDropDown()
        }


        // periode tabungan
        var periodeTabungan = etPeriodeTabungan as AutoCompleteTextView
        fun dataPeriodeTabungan() {
            var list = listOf("Harian", "Mingguan", "Bulanan")
            var adapter =
                ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list)
            periodeTabungan.setAdapter(adapter)
            periodeTabungan.threshold = 1
        }

        periodeTabungan.setOnClickListener {
            dataPeriodeTabungan()
            periodeTabungan.showDropDown()
        }

        presenter =
            FormTabunganTwoPresenter(
                this
            )
        etTanggal.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(
                    etTanggal.text.toString(),
                    etSetoranAwal.text.toString(),
                    etMetodeTabungan.text.toString(),
                    etPeriodeTabungan.text.toString(),
                    etJumlahTabungan.text.toString()
                )
            }
        })

        etSetoranAwal.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(
                    etTanggal.text.toString(),
                    etSetoranAwal.text.toString(),
                    etMetodeTabungan.text.toString(),
                    etPeriodeTabungan.text.toString(),
                    etJumlahTabungan.text.toString()
                )
            }
        })

        etMetodeTabungan.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(
                    etTanggal.text.toString(),
                    etSetoranAwal.text.toString(),
                    etMetodeTabungan.text.toString(),
                    etPeriodeTabungan.text.toString(),
                    etJumlahTabungan.text.toString()
                )
            }
        })

        etPeriodeTabungan.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(
                    etTanggal.text.toString(),
                    etSetoranAwal.text.toString(),
                    etMetodeTabungan.text.toString(),
                    etPeriodeTabungan.text.toString(),
                    etJumlahTabungan.text.toString()
                )
            }
        })

        etJumlahTabungan.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(
                    etTanggal.text.toString(),
                    etSetoranAwal.text.toString(),
                    etMetodeTabungan.text.toString(),
                    etPeriodeTabungan.text.toString(),
                    etJumlahTabungan.text.toString()
                )
            }
        })


    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun showDatePicker() {
        // DatePicker
        DateEditText.setText(SimpleDateFormat("dd-MMM-yyyy").format(System.currentTimeMillis()))

        var cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "EEEE-MMM-yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                DateEditText.setText(sdf.format(cal.time))
            }

        DateEditText.setOnClickListener {

            Log.d("Clicked", "Interview Date Clicked")

            val dialog = DatePickerDialog(
                this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            dialog.datePicker.maxDate =
                CalendarHelper.getCurrentDateInMills()
            dialog.show()
        }
    }

    override fun btnActive() {
        btnLanjutForm.setBackgroundResource(R.drawable.bg_active)
        btnLanjutForm.setTextColor(Color.parseColor("#ffffff"))
        btnLanjutForm.setElevation(2f)
        btnLanjutForm.isClickable = true

//        btnLanjutForm2.setOnClickListener {
//            val goToFormTabunganThree = Intent(this, FormTabunganActivityThree::class.java)
//            startActivity(goToFormTabunganThree)
//        }
    }

    override fun btnInactive() {
        btnLanjutForm.setBackgroundResource(R.drawable.bg_inactive)
        btnLanjutForm.isClickable = false
    }
}