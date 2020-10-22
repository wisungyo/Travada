package com.example.travada.features.rencana.searchpage

import com.example.travada.features.rencana.searchpage.TPSearchPageActivity.Companion.isFilter
import com.example.travada.features.rencana.searchpage.room.DatabaseItem
import com.example.travada.features.rencana.searchpage.room.ItemSearch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TPSearchPagePresenter(val db: DatabaseItem, val listener:Listener) {


    fun addToQueryHistory(item: ItemSearch) {
        GlobalScope.launch {
            db.itemDao().addToHistory(item)
        }
    }

    fun onQuerySearch(query:String) {
        TPSearchPageActivity.isSearch = true
        isFilter = false
        TPSearchPageActivity.searchQuery = query
        val item = ItemSearch(null, query.toString())
        addToQueryHistory(item)
        listener.showSearchResult()
    }

    interface Listener {
        fun showSearchResult()
        fun showSearchSuggest()
        fun goToFilterActivity()
        fun showToast(text:String)
    }
}