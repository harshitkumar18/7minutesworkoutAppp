package com.example.a7minutesworkoutapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistroyDao {

    @Insert
    suspend fun insert(histroyEntity: HistroyEntity)

    @Query("Select * FROM 'history-table'")
    fun fetchalldates(): Flow<List<HistroyEntity>>


}