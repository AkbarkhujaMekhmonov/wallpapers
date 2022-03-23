package com.example.wallpapers.models.saved

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SavedImg::class], version = 1)
abstract class MyDataBase: RoomDatabase() {
    abstract fun Dao():MyDao
    companion object{
        private var instens:MyDataBase?=null

        @Synchronized
        fun getInstants(context: Context):MyDataBase{
            if (instens==null){
                instens = Room.databaseBuilder(context, MyDataBase::class.java, "room")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build()
            }
            return instens!!
        }
    }
}