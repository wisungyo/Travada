package com.example.travada.features.tabungan.formtabungandua

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.icu.text.NumberFormat
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
import com.example.travada.features.tabungan.models.DataTabungBareng
import com.example.travada.features.tabungan.formtabungantiga.FormTabunganThreeActivity
import com.example.travada.features.tabungan.formresulttabungan.DetailFormResultActivity
import kotlinx.android.synthetic.main.activity_form_tabungan_one.*
import kotlinx.android.synthetic.main.activity_form_tabungan_two.*
import kotlinx.android.synthetic.main.activity_tabungan.*
import java.util.*

class FormTabunganTwoActivity : AppCompatActivity(), FormTabunganTwoPresenter.Listener {
    lateinit var DateEditText: EditText
    private lateinit var presenter: FormTabunganTwoPresenter
    private lateinit var terimaBundle: Bundle

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_tabungan_two)

        intent?.extras?.let { terimaBundle = it }
        terimaBundle = Bundle()

        presenter = FormTabunganTwoPresenter(this)
        presenter.fetchTabungBarengData()
        //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        btnTambahTeman.setOnClickListener {
            val goToFormTambahTeman = Intent(this, FormTabunganThreeActivity::class.java)
            startActivity(goToFormTambahTeman)
        }

        btnLanjutFormTwo.setOnClickListener {
            presenter.goToNextPage(terimaBundle)
        }

        DateEditText = findViewById(R.id.etTanggal)
        showDatePicker()

        ivFormTwoBack.setOnClickListener {
            finish()
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

        etTanggal.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(
                    etTanggal.text.toString(),
                    etSetoranAwal.text.toString(),
                    etMetodeTabungan.text.toString(),
                    etPeriodeTabungan.text.toString(),
                    etJumlahSetoran.text.toString()
                )
            }
        })

        etSetoranAwal.addTextChangedListener(object : TextWatcher {
            var processed = ""

            @RequiresApi(Build.VERSION_CODES.N)
            override fun afterTextChanged(count: Editable?) {
                val initial = count.toString()

                if (etSetoranAwal == null) return
                if (initial.isEmpty()) return

                val cleanString = initial.replace(".", "")
                val nf = NumberFormat.getNumberInstance(Locale.GERMAN)
                nf.setGroupingUsed(true);

                var myNumber = cleanString.toDouble()
                processed = nf.format(myNumber)
                etSetoranAwal.removeTextChangedListener(this)
                etSetoranAwal.setText(processed)

                try {
                    etSetoranAwal.setSelection(processed.length)
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
                etSetoranAwal.addTextChangedListener(this)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(
                    etTanggal.text.toString(),
                    etSetoranAwal.text.toString(),
                    etMetodeTabungan.text.toString(),
                    etPeriodeTabungan.text.toString(),
                    etJumlahSetoran.text.toString()
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
                    etJumlahSetoran.text.toString()
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
                    etJumlahSetoran.text.toString()
                )
            }
        })

        etJumlahSetoran.addTextChangedListener(object : TextWatcher {
            var processed = ""
            @RequiresApi(Build.VERSION_CODES.N)
            override fun afterTextChanged(count: Editable?) {
                val initial = count.toString()
                if (etJumlahSetoran == null) return
                if (initial.isEmpty()) return

                val cleanString = initial.replace(".", "")
                val nf = NumberFormat.getNumberInstance(Locale.GERMAN)
                nf.setGroupingUsed(true);

                var myNumber = cleanString.toDouble()
                processed = nf.format(myNumber)
                etJumlahSetoran.removeTextChangedListener(this)
                etJumlahSetoran.setText(processed)

                try {
                    etJumlahSetoran.setSelection(processed.length)
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
                etJumlahSetoran.addTextChangedListener(this)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(
                    etTanggal.text.toString(),
                    etSetoranAwal.text.toString(),
                    etMetodeTabungan.text.toString(),
                    etPeriodeTabungan.text.toString(),
                    etJumlahSetoran.text.toString()
                )
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun showDatePicker() {
        // DatePicker
        DateEditText.setText(SimpleDateFormat("dd MMMM yyyy").format(System.currentTimeMillis()))
        var cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd MMMM yyyy" // mention the format you need
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
            dialog.datePicker.minDate =
                CalendarHelper.getCurrentDateInMills()
            dialog.show()
        }
    }

    override fun btnActive() {
        btnLanjutFormTwo.setBackgroundResource(R.drawable.bg_active)
        btnLanjutFormTwo.setTextColor(Color.parseColor("#ffffff"))
        btnLanjutFormTwo.setElevation(2f)
        btnLanjutFormTwo.isClickable = true
    }



    override fun goToNextPage(bundle:Bundle) {
        val intent = Intent(this, DetailFormResultActivity::class.java)
        bundle.putString("tanggalTarget", etTanggal.text.toString())
        bundle.putString("setoranAwal", etSetoranAwal.text.toString())
        bundle.putString("metodeTabungan", etMetodeTabungan.text.toString())
        bundle.putString("periodeTabungan", etPeriodeTabungan.text.toString())
        bundle.putString("jumlahSetoran", etJumlahSetoran.text.toString())
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun btnInactive() {
        btnLanjutFormTwo.setBackgroundResource(R.drawable.bg_inactive)
        btnLanjutFormTwo.isClickable = false
    }

    override fun showDataTabungBareng(adapterTabungBareng: BarengTemanAdapter) {
        val layoutManagerLinear =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTabunganBarengTeman.layoutManager = layoutManagerLinear
        rvTabunganBarengTeman.adapter = adapterTabungBareng
        rvTabunganBarengTeman.overScrollMode = View.OVER_SCROLL_NEVER
    }

}