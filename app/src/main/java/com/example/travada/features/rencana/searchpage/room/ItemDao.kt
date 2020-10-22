package com.example.travada.features.rencana.searchpage.room

import androidx.room.*

@Dao
interface ItemDao {
    @Query("SELECT * FROM ItemSearch ORDER BY id DESC ")
    fun getAllHistory(): List<ItemSearch>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToHistory(item: ItemSearch): Long

    @Delete
    fun deleteFromHistory(item: ItemSearch): Int

    @Query("DELETE FROM ItemSearch")
    fun deleteAllHistory()
}