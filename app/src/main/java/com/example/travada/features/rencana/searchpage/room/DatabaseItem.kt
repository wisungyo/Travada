package com.example.travada.features.rencana.searchpage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(ItemSearch::class)], version = 1)
abstract class DatabaseItem: RoomDatabase() {
    abstract fun itemDao() : ItemDao

    companion object{
        private var INSTANCE: DatabaseItem? = null

        fun getInstance(context: Context): DatabaseItem? {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseItem::class.java,
                    "query_db").build()
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}