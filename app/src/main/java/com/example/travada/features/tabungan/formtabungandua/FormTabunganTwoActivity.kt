package com.example.travada.features.tabungan.formtabungandua

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.icu.text.NumberFormat
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.BarengTemanAdapter
import com.example.travada.features.tabungan.formresulttabungan.DetailFormResultActivity
import com.example.travada.features.tabungan.formtabungantiga.FormTabunganThreeActivity
import com.example.travada.features.tabungan.helper.CalendarHelper
import com.example.travada.features.tabungan.models.DataTabungBareng
import kotlinx.android.synthetic.main.activity_form_tabungan_two.*
import kotlinx.android.synthetic.main.activity_register1.*
import kotlinx.android.synthetic.main.item_t_p_search_page.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.properties.Delegates


class FormTabunganTwoActivity : AppCompatActivity(), FormTabunganTwoPresenter.Listener,
    AdapterView.OnItemClickListener {
    lateinit var DateEditText: EditText
    private lateinit var presenter: FormTabunganTwoPresenter
    private lateinit var terimaBundle: Bundle
    lateinit var listTabungBareng: ArrayList<DataTabungBareng>

    lateinit var namaTambah: ArrayList<String>
    lateinit var nomerRekeningTambah: ArrayList<String>
    lateinit var inisialTambah: ArrayList<String>
    var setorInt:Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_tabungan_two)

        intent?.extras?.let { terimaBundle = it }

        // jumlahditabung
        terimaBundle = Bundle()
        multi = false

        presenter = FormTabunganTwoPresenter(this)
//          presenter.fetchTabungBarengData()
        //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        listTabungBareng = ArrayList<DataTabungBareng>()
        namaTambah =  ArrayList<String>()
        nomerRekeningTambah = ArrayList<String>()
        inisialTambah = ArrayList<String>()

        btnTambahTeman.setOnClickListener {
//            val goToFormTambahTeman = Intent(this, FormTabunganThreeActivity::class.java)
//            startActivity(goToFormTambahTeman)
            val intent = Intent(this, FormTabunganThreeActivity::class.java)
            this.startActivityForResult(intent, 12)
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
            var list = listOf("Auto debet")
            var adapter =
                ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list)
            metodeTabungan.setAdapter(adapter)
            metodeTabungan.threshold = 1
            checkingJumlahSetoran()
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
            periodeTabungan.onItemSelectedListener
            checkingJumlahSetoran()
        }

        periodeTabungan.setOnClickListener {
            dataPeriodeTabungan()
            periodeTabungan.showDropDown()
        }


        etTanggal.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(
                    etTanggal.text.toString(),
                    etSetoranAwal.text.toString(),
                    etMetodeTabungan.text.toString(),
                    etPeriodeTabungan.text.toString()
                )
                checkingJumlahSetoran()
            }
        })

        etSetoranAwal.addTextChangedListener(object : TextWatcher {
            var processed = ""

            @RequiresApi(Build.VERSION_CODES.O)
            override fun afterTextChanged(count: Editable?) {
                if (etSetoranAwal == count) {
                    etSetoranAwal.clearFocus()
                    // etSetoranAwal.requestFocus()
                    etSetoranAwal.isCursorVisible
                }

                if (count.toString().length == 1 && count.toString().startsWith("0")) {
                    count?.clear();
                }

                val initial = count.toString()
                if (etSetoranAwal == null) return
                if (initial.isEmpty()) return

                val cleanString = initial.replace(".", "")
                val nf = NumberFormat.getNumberInstance(Locale.GERMAN)
                nf.setGroupingUsed(true);

                var myNumber = cleanString.toLong()
                setoranAwal - cleanString.toLong()
                processed = nf.format(myNumber)
                etSetoranAwal.removeTextChangedListener(this)
                etSetoranAwal.setText(processed)

                try {
                    etSetoranAwal.setSelection(processed.length)
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
                etSetoranAwal.addTextChangedListener(this)
                checkingJumlahSetoran()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                errSetoranAwal(null)
                presenter.checkSetoranAwal(etSetoranAwal.text.toString())
                presenter.checked(
                    etTanggal.text.toString(),
                    etSetoranAwal.text.toString(),
                    etMetodeTabungan.text.toString(),
                    etPeriodeTabungan.text.toString()
                )
            }
        })

        etMetodeTabungan.addTextChangedListener(object : TextWatcher {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun afterTextChanged(p0: Editable?) {
                checkingJumlahSetoran()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(
                    etTanggal.text.toString(),
                    etSetoranAwal.text.toString(),
                    etMetodeTabungan.text.toString(),
                    etPeriodeTabungan.text.toString()
                )
            }
        })

        etSetoranAwal.setOnFocusChangeListener(OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                // code to execute when EditText loses focus
                val imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        })

        etPeriodeTabungan.addTextChangedListener(object : TextWatcher {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun afterTextChanged(p0: Editable?) {
                checkingJumlahSetoran()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checked(
                    etTanggal.text.toString(),
                    etSetoranAwal.text.toString(),
                    etMetodeTabungan.text.toString(),
                    etPeriodeTabungan.text.toString()
                )
            }
        })

//        etJumlahSetoran.addTextChangedListener(object : TextWatcher {
//            var processed = ""
//
//            @RequiresApi(Build.VERSION_CODES.N)
//            override fun afterTextChanged(count: Editable?) {
//                if (count.toString().length == 1 && count.toString().startsWith("0")) {
//                    count?.clear();
//                }
//
//                val initial = count.toString()
//                if (etJumlahSetoran == null) return
//                if (initial.isEmpty()) return
//
//                val cleanString = initial.replace(".", "")
//                val nf = NumberFormat.getNumberInstance(Locale.GERMAN)
//                nf.setGroupingUsed(true);
//
//                var myNumber = cleanString.toLong()
//                jumlahSetoran = cleanString.toLong()
//                processed = nf.format(myNumber)
//                etJumlahSetoran.removeTextChangedListener(this)
//                etJumlahSetoran.setText(processed)
//
//                try {
//                    etJumlahSetoran.setSelection(processed.length)
//                } catch (e: NumberFormatException) {
//                    e.printStackTrace()
//                }
//                etJumlahSetoran.addTextChangedListener(this)
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                presenter.checked(
//                    etTanggal.text.toString(),
//                    etSetoranAwal.text.toString(),
//                    etMetodeTabungan.text.toString(),
//                    etPeriodeTabungan.text.toString(),
//                    etJumlahSetoran.text.toString()
//                )
//            }
//        })


//        etJumlahSetoran.setOnFocusChangeListener(OnFocusChangeListener { v, hasFocus ->
//            if (!hasFocus) {
//                // code to execute when EditText loses focus
//                val imm: InputMethodManager =
//                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                imm.hideSoftInputFromWindow(v.windowToken, 0)
//            }
//        })

        btnInactive()
    }

    //    @RequiresApi(Build.VERSION_CODES.N)
    @RequiresApi(Build.VERSION_CODES.O)
    fun hitung() {

        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val date = sdf.parse(etTanggal.text.toString())

        var setoranAwalRaw = etSetoranAwal.text.toString()
        var setoranAwal = setoranAwalRaw.replace(".", "").toInt()


        var jumlahTabungRaw = intent.getStringExtra("jumlahDitabung").toString()
        var jumlahTabung = jumlahTabungRaw.replace(".", "").toInt()

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = current.format(formatter)
        val dateNow = sdf.parse(formatted)

        var tanggalSekarangHarian = date.time - dateNow.time
//        var selisihTanggalHarian = java.util.concurrent.TimeUnit.MILLISECONDS.toDays(tanggalSekarangHarian)
        var selisihTanggalHarian =
            TimeUnit.DAYS.convert(tanggalSekarangHarian, TimeUnit.MILLISECONDS)

        var tanggalSekarangMingguan = date.time - dateNow.time
//        var selisihTanggalMingguan = java.util.concurrent.TimeUnit.MILLISECONDS.toDays(tanggalSekarangMingguan)/7
        var selisihTanggalMingguan =
            TimeUnit.DAYS.convert(tanggalSekarangMingguan, TimeUnit.MILLISECONDS) / 7

        var tanggalSekarangBulanan = date.time - dateNow.time
//        var selisihTanggalBulanan = java.util.concurrent.TimeUnit.MILLISECONDS.toDays(tanggalSekarangBulanan)/30
        var selisihTanggalBulanan =
            TimeUnit.DAYS.convert(tanggalSekarangBulanan, TimeUnit.MILLISECONDS) / 30

        var setor: Double = 0.0
        var orang = 1

        if (selisihTanggalHarian.toString() == "0") {
            selisihTanggalHarian = 1
        }
        if (selisihTanggalMingguan.toString() == "0") {
            selisihTanggalMingguan = 1
        }
        if (selisihTanggalBulanan.toString() == "0") {
            selisihTanggalBulanan = 1
        }

        orang = listTabungBareng.size + 1


        Log.d("errcheck", "==========================")
        Log.d("errcheck", listTabungBareng.size.toString())
        Log.d("errcheck", orang.toString())
        Log.d("errcheck", jumlahTabung.toString())
        Log.d("errcheck", setoranAwal.toString())
        Log.d("errcheck", selisihTanggalHarian.toString())
        Log.d(
            "errcheck",
            "(jumlahTabung - (setoranAwal * orang)" + ((jumlahTabung - (setoranAwal * orang)).toDouble()).toString()
        )
        Log.d(
            "errcheck",
            "(selisihTanggalHarian / orang)" + (selisihTanggalHarian.toDouble() / orang.toDouble()).toString()
        )
        Log.d(
            "errcheck",
            "Hasil" + ((jumlahTabung - (setoranAwal * orang)).toDouble() / (selisihTanggalHarian.toDouble() / orang.toDouble())).toString()
        )

        val periodeNew = etPeriodeTabungan.text.toString()
        Log.d("SETORAN", "periode=${periodeNew}")
        if (periodeNew == "Harian") {
            Log.d("SETORAN", "if executed harian")
//            setor = jumlahTabung-(setoranAwal*orang)/(selisihTanggalHarian-tanggalSekarangHarian)*orang
            setor =
                ((jumlahTabung - (setoranAwal * orang)).toDouble() / (selisihTanggalHarian.toDouble() * orang.toDouble()).toDouble()).toDouble()
        } else if (periodeNew == "Mingguan") {
            Log.d("SETORAN", "if executed mingguan")
//            setor = jumlahTabung-(setoranAwal*orang)/(selisihTanggalMingguan-tanggalSekarangMingguan)* orang
            setor =
                ((jumlahTabung - (setoranAwal * orang)).toDouble() / (selisihTanggalMingguan.toDouble() * orang.toDouble()).toDouble()).toDouble()
        } else if (periodeNew == "Bulanan") {
            Log.d("SETORAN", "if executed bulanan")
//            setor = jumlahTabung-(setoranAwal*orang)/(selisihTanggalBulanan-tanggalSekarangBulanan)* orang
            setor =
                ((jumlahTabung - (setoranAwal * orang)).toDouble() / (selisihTanggalBulanan.toDouble() * orang.toDouble()).toDouble()).toDouble()
        }

        setorInt = setor.toInt()

//        Log.d("SETORAN", "tglAwal=${formatted}")
//        Log.d("SETORAN", "tglAwal=${dateNow.time}")
//        Log.d("SETORAN", "tglAkhir=${etTanggal.text.toString()}")
//        Log.d("SETORAN", "tglAkhir=${date.time}")
//        Log.d("SETORAN", "tglSekarangHarian=${tanggalSekarangHarian}")
//        Log.d("SETORAN", "tglSekarangMingguan=${tanggalSekarangMingguan}")
//        Log.d("SETORAN", "tglSekarangBulanan=${tanggalSekarangBulanan}")
//        Log.d("SETORAN", "jumlahTabung=${jumlahTabung}")
//        Log.d("SETORAN", "setoranAwal=${setoranAwal}")
//        Log.d("SETORAN", "orang=${orang}")
//        Log.d("SETORAN", "selisihTanggalHarian=${selisihTanggalHarian}")
//        Log.d("SETORAN", "selisihTanggalMingguan=${selisihTanggalMingguan}")
//        Log.d("SETORAN", "selisihTanggalBulanan=${selisihTanggalBulanan}")
//        Log.d("SETORAN", "setor=${setor}")
//        Log.d("SETORAN", "setorInt=${setorInt}")
//        Log.d(
//            "SETORAN",
//            "hasil=${(jumlahTabung - (setoranAwal * orang)) / (selisihTanggalBulanan * orang)}"
//        )
////        etJumlahSetoran.text =
////            "${(jumlahTabung - (setoranAwal * orang)) / (selisihTanggalBulanan * orang)}"
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)

        layoutEtJumlahTabungan.text = "Jumlah Setoran ${numberFormat.format(setorInt)}"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkingJumlahSetoran() {
        val tanggal = etTanggal.text.toString()
        val setoranAwal = etSetoranAwal.text.toString()
        val metodeTabungan = etMetodeTabungan.text.toString()
        val periodeTabungan = etPeriodeTabungan.text.toString()

        if (tanggal.isNotEmpty() && setoranAwal.isNotEmpty() && metodeTabungan.isNotEmpty() && periodeTabungan.isNotEmpty()) {
            hitung()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 12) {
            if (resultCode == Activity.RESULT_OK) {
                multi = true
                data?.let {
                    val body = DataTabungBareng(
                        it.getStringExtra(FormTabunganThreeActivity.namaRekening).toString(),
                        it.getStringExtra(FormTabunganThreeActivity.nomorRekening).toString(),
                        it.getStringExtra(FormTabunganThreeActivity.namaRekening)!!
                            .subSequence(0, 1).toString()
                    )

                    namatambah =
                        it.getStringExtra(FormTabunganThreeActivity.namaRekening).toString()
                    rekeningtambah =
                        it.getStringExtra(FormTabunganThreeActivity.nomorRekening).toString()

                    namaTambah.add(it.getStringExtra(FormTabunganThreeActivity.namaRekening).toString())
                    nomerRekeningTambah.add(it.getStringExtra(FormTabunganThreeActivity.nomorRekening).toString())
                    inisialTambah.add(it.getStringExtra(FormTabunganThreeActivity.namaRekening)!!
                        .subSequence(0, 1).toString())

                    listTabungBareng.add(body)
                    Log.d("errcheck", listTabungBareng.toString())
                }
                val adapterTabungBareng = BarengTemanAdapter(listTabungBareng)
                showDataTabungBareng(adapterTabungBareng)
            }
        }
        checkingJumlahSetoran()
    }

    override fun onResume() {
        super.onResume()
//        presenter
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun showDatePicker() {
        // DatePicker
        DateEditText.setText(SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis()))
        var cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd-MM-yyyy" // mention the format you need
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

    override fun goToNextPage(bundle: Bundle) {
        val uriGambar = intent.getStringExtra("uriGambar")
        val jumlahDitabung = intent.getStringExtra("jumlahDitabung")
        val tujuan = intent.getStringExtra("namaTujuan")

        val intent = Intent(this, DetailFormResultActivity::class.java)
//        bundle.putString("tanggalTarget", etTanggal.text.toString())
//        bundle.putString("setoranAwal", etSetoranAwal.text.toString())
//        bundle.putString("metodeTabungan", etMetodeTabungan.text.toString())
//        bundle.putString("periodeTabungan", etPeriodeTabungan.text.toString())
//        bundle.putString("jumlahSetoran", etJumlahSetoran.text.toString())
//        intent.putExtras(bundle)
        val bundle = Bundle()
        bundle.putString("uriGambar", uriGambar)
        bundle.putString("jumlahDitabung", jumlahDitabung)
        bundle.putString("namaTujuan", tujuan)
        bundle.putString("tanggalTarget", etTanggal.text.toString())
        bundle.putLong("setoranAwal", setoranAwal)
        bundle.putString("metodeTabungan", etMetodeTabungan.text.toString())
        bundle.putString("periodeTabungan", etPeriodeTabungan.text.toString())
        bundle.putInt("jumlahSetoran", setorInt)
        bundle.putString("namatambah", namatambah)
        bundle.putString("rekeningtambah", rekeningtambah)
        intent.putExtras(bundle)

        intent.getStringExtra("jumlahDitabung").toString()
        intent.putExtra("uriGambar", uriGambar)
        intent.putExtra("jumlahDitabung", jumlahDitabung)
        intent.putExtra("namaTujuan", tujuan)
        intent.putExtra("tanggalTarget", etTanggal.text.toString())
        intent.putExtra("setoranAwal", etSetoranAwal.text.toString())
        intent.putExtra("metodeTabungan", etMetodeTabungan.text.toString())
        intent.putExtra("periodeTabungan", etPeriodeTabungan.text.toString())
        intent.putExtra("jumlahSetoran", setorInt.toString())
//        intent.putExtra("namaTambah", namaTambah)
//        intent.putExtra("nomerRekeningTambah", nomerRekeningTambah)
//        intent.putExtra("inisialTambah", inisialTambah)
        intent.putExtra("DataList", listTabungBareng)


        startActivity(intent)

        var namatambah = ""
        var rekeningtambah = ""
    }

    override fun btnInactive() {
        btnLanjutFormTwo.setBackgroundResource(R.drawable.bg_inactive)
        btnLanjutFormTwo.isClickable = false
    }

    override fun errSetoranAwal(message: String?) {
        layoutEtSetoranAwal.error = message
    }

    override fun showDataTabungBareng(adapterTabungBareng: BarengTemanAdapter) {
        val layoutManagerLinear =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTabunganBarengTeman.layoutManager = layoutManagerLinear
        rvTabunganBarengTeman.adapter = adapterTabungBareng
        rvTabunganBarengTeman.overScrollMode = View.OVER_SCROLL_NEVER
    }

    companion object {
        var jumlahSetoran = 0L
        var setoranAwal = 0L
        var periode = ""
        var multi = false
        var namatambah = ""
        var rekeningtambah = ""
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val item = parent.getString(position)
        periode = item
    }

}