package com.doancs3_new.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress_logs")
data class ProgressLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val date: String, // "yyyy-MM-dd"
    val weight: Int
)
