package com.example.a7minutesworkoutapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="history-table")
data class HistroyEntity(
    @PrimaryKey
    val date:String
)
