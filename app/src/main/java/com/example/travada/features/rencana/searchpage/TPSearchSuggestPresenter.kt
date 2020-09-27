package com.example.travada.features.rencana.searchpage

import com.example.travada.features.rencana.searchpage.TPSearchPageActivity.Companion.isFilter
import com.example.travada.features.rencana.searchpage.room.DatabaseItem
import com.example.travada.features.rencana.searchpage.room.ItemSearch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TPSearchSuggestPresenter(val db: DatabaseItem, val listener:Listener) {

    fun fetchData(){
        GlobalScope.launch {
            val list = db.itemDao().getAllHistory()
            listener.showQueryHistory(list)
        }
    }

    fun deleteItem(item: ItemSearch){
        GlobalScope.launch {
            db.itemDao().deleteFromHistory(item)
            listener.onDelete(item)
        }
    }

    fun deleteAll(){
        GlobalScope.launch {
            db.itemDao().deleteAllHistory()
            fetchData()
        }
    }

    fun onQuerySearch(query:String) {
        TPSearchPageActivity.isSearch = true
        isFilter = false
        TPSearchPageActivity.searchQuery = query
        listener.showSearchResult()
    }

    interface Listener {
        fun showQueryHistory(listItem: List<ItemSearch>)
        fun onDelete(item: ItemSearch)
        fun showSearchResult()
    }
}