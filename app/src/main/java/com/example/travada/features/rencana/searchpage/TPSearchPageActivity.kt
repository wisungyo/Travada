package com.example.travada.features.rencana.searchpage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.travada.R
import com.example.travada.features.rencana.searchpage.room.DatabaseItem
import com.example.travada.features.rencana.searchpage.room.ItemSearch
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.welcomepage.register.register2.Register2Activity
import com.example.travada.welcomepage.register.takepicKTP.TakePicKTPActivity
import kotlinx.android.synthetic.main.activity_t_p_search_page.*


class TPSearchPageActivity : AppCompatActivity(), TPSearchPagePresenter.Listener {
    private lateinit var presenter: TPSearchPagePresenter
    val LoadingDialog = LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_t_p_search_page)

        showSearchResult()

        DatabaseItem.getInstance(this)?.let {
            presenter = TPSearchPagePresenter(it, this)
        }


        btn_back.setOnClickListener {
            isSearch = false
            isFilter = false
            searchQuery = ""
            minValue = 0
            maxValue = 5000000
            benua = ""

            finish()
        }

        search_dummy.setOnClickListener {
            showSearchSuggest()
        }

        extended_fab.setOnClickListener {
            goToFilterActivity()
        }

        search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    showSearchResult()
                    return false
                } else {
                    presenter.onQuerySearch(query)
                    return true
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            isFilter = true
            isSearch = false
            data?.let {
                benua = it.getStringExtra("benua")
                minValue = it.getIntExtra("minFilter", 0)
                maxValue = it.getIntExtra("maxFilter", 50000000)
            }
            showSearchResult()
        }
    }

    override fun showSearchResult() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_tp_searchpage, TpSearchResultFragment())
            .commit()
        extended_fab.visibility = VISIBLE
        search_dummy.visibility = VISIBLE
        search.visibility = INVISIBLE
        if (isSearch == false && isFilter == true) {
            extended_fab.backgroundTintList = AppCompatResources.getColorStateList(this, R.color.inactiveblue)
            search_dummy.hint = null
            search.setQuery(null, false)
        } else {
            extended_fab.backgroundTintList = AppCompatResources.getColorStateList(this, R.color.white)
            if (searchQuery.isNotEmpty()) {
                search_dummy.hint = searchQuery
                search.setQuery(searchQuery, false)
            }
        }

    }

    override fun showSearchSuggest() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_tp_searchpage, TpSearchSuggestFragment())
            .commit()
        extended_fab.visibility = INVISIBLE
        search_dummy.visibility = INVISIBLE
        search.visibility = VISIBLE
        search.requestFocus()
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    override fun goToFilterActivity() {
        val intent = Intent(this, TPFilterPageActivity::class.java)
        this.startActivityForResult(intent, requestFilterCode)
    }

    override fun showToast(text: String) {
        Toast.makeText(
            this@TPSearchPageActivity, text,
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        var isSearch = false
        var isFilter = false
        var searchQuery = ""
        var minValue = 0
        var maxValue = 5000000
        var benua:String? = ""
        private val requestFilterCode = 808
    }
}