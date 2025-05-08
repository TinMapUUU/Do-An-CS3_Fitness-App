package com.doancs3_new.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String, // Firebase UID
    val email: String,
    val name: String,
    val height: Int,
    val currentWeight: Int,
    val targetWeight: Int
)
