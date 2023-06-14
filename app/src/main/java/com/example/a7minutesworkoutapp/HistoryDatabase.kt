package com.example.a7minutesworkoutapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [HistroyEntity::class], version = 1)
abstract class HistoryDatabase: RoomDatabase() {

    abstract  fun historyDAO(): HistroyDao
    companion object{
        @Volatile
        private var Instance: HistoryDatabase? = null

        fun getInstance(context: Context):HistoryDatabase{
            synchronized(this){
                var instance = Instance

                if(instance==null){
                    instance = Room.databaseBuilder(context.applicationContext,HistoryDatabase::class.java,
                        "history_database")
                        .fallbackToDestructiveMigration().build()

                    Instance = instance
                }
                return instance
            }


        }

    }

}