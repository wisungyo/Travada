package com.example.travada.features.tabungan.formtabungandua

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
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.BarengTemanAdapter
import com.example.travada.features.tabungan.helper.CalendarHelper
import com.example.travada.features.tabungan.formdetailtabungan.DataTabungBareng
import com.example.travada.features.tabungan.formtabungantiga.FormTabunganThreeActivity
import com.example.travada.features.tabungan.formtabungansatu.FormTabunganOneActivity
import com.example.travada.features.tabungan.formdetailtabungan.DetailFormResultActivity
import kotlinx.android.synthetic.main.activity_form_tabungan_two.*
import java.util.*

class FormTabunganTwoActivity : AppCompatActivity(),
    FormTabunganTwoPresenter.Listener {

    private val listTabungBareng = arrayListOf(
        DataTabungBareng("Nanda Adi", "1212131", "AN"),
        DataTabungBareng("Abigail", "1212122", "A"),
        DataTabungBareng("Nicholas", "3434343", "N")
    )

    val adapterBarengTeman =
        BarengTemanAdapter(
            listTabungBareng
        )

    lateinit var DateEditText: EditText
    private lateinit var presenter: FormTabunganTwoPresenter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_tabungan_two)

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        val layoutManagerLinear = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvBarengTeman.layoutManager = layoutManagerLinear
        rvBarengTeman.adapter = adapterBarengTeman
        rvBarengTeman.overScrollMode = View.OVER_SCROLL_NEVER

        btnTambahTeman.setOnClickListener {
            val goToFormTambahTeman = Intent(this, FormTabunganThreeActivity::class.java)
            startActivity(goToFormTambahTeman)
        }

        DateEditText = findViewById(R.id.etTanggal)
        showDatePicker()

        ivFormTwoBack.setOnClickListener {
            val backToTabungan = Intent(this, FormTabunganOneActivity::class.java)
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

        presenter = FormTabunganTwoPresenter(this)
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
        btnLanjutFormTwo.setBackgroundResource(R.drawable.bg_active)
        btnLanjutFormTwo.setTextColor(Color.parseColor("#ffffff"))
        btnLanjutFormTwo.setElevation(2f)
        btnLanjutFormTwo.isClickable = true

        btnLanjutFormTwo.setOnClickListener {
            val goToFormTabunganDetail = Intent(this, DetailFormResultActivity::class.java)
            startActivity(goToFormTabunganDetail)
        }
    }

    override fun btnInactive() {
        btnLanjutFormTwo.setBackgroundResource(R.drawable.bg_inactive)
        btnLanjutFormTwo.isClickable = false
    }
}