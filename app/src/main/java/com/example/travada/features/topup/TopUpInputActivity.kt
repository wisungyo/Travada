package com.example.travada.features.topup

import android.content.Intent
import android.graphics.Color
import android.icu.text.NumberFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.travada.R
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.util.util
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_top_up_input.*
import kotlinx.android.synthetic.main.item_t_p_search_page.view.*
import java.util.*

class TopUpInputActivity : AppCompatActivity(), TopUpInputPresenter.Listener {
    private lateinit var presenter: TopUpInputPresenter
    val MyFragment= LoadingDialog()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_input)
        presenter = TopUpInputPresenter(this)

        var myNumber = 0L

        presenter.setInformation()

        et_accountnumb.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.checket(et_accountnumb.text.toString(), myNumber.toString())
            }
        })

        et_topup_nominal.addTextChangedListener(object:TextWatcher{
            var processed = ""


            @RequiresApi(Build.VERSION_CODES.N)
            override fun afterTextChanged(p0: Editable?) {
                val initial = p0.toString()
                if (et_topup_nominal == null) return
                if (initial.isEmpty()) return
                val cleanString = initial.replace(".", "")
                val nf = NumberFormat.getNumberInstance(Locale.GERMAN)
                nf.setGroupingUsed(true);
                myNumber = cleanString.toLong()
                processed = nf.format(myNumber)
                et_topup_nominal.removeTextChangedListener(this)
                et_topup_nominal.setText(processed)

                try {
                    et_topup_nominal.setSelection(processed.length)
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
                et_topup_nominal.addTextChangedListener(this)

                presenter.checknominal(myNumber)
                presenter.checket(et_accountnumb.text.toString(), myNumber.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }
        })

        btn_next.setOnClickListener {
            showBottomSheetDialog(myNumber)
        }

        btn_back.setOnClickListener { finish() }

        btnInactive()
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

    override fun goToNextPage(value: Long) {
        val intent = Intent(this, TopUpInputPinActivity::class.java)
        val bundle = Bundle()
        bundle.putString("nominal", value.toString())
        bundle.putString("accountnumb", et_accountnumb.text.toString())
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun showLoadingDialog() {
        val fm=supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun errAccountnumb(text: String?) {
        til_accountnumb.error = text
    }

    override fun errNominal(text: String?) {
        til_topup_nominal.error =text
    }

    override fun showToast(text: String) {
        Toast.makeText(
            this@TopUpInputActivity, text,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showBottomSheetDialog(value: Long) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_top_up_confirm, null)

        val batal = view.findViewById<ImageView>(R.id.iv_close)
        val nominal = view.findViewById<TextView>(R.id.tv_topup_nominal)
        val total = view.findViewById<TextView>(R.id.tv_total)
        val konfirmasi = view.findViewById<Button>(R.id.btn_next)

        batal.setOnClickListener { dialog.dismiss() }

        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        nominal.text = numberFormat.format(value)
        total.text = numberFormat.format(value)

        konfirmasi.setOnClickListener {
            goToNextPage(value)
        }

        dialog.setCancelable(true)
        dialog.setContentView(view)
        dialog.show()
    }

    override fun setInformation(numb: String, name: String) {
        tv_topup_username.text = name
        tv_topup_id.text = numb
    }


    companion object {
        var isError = false
    }
}