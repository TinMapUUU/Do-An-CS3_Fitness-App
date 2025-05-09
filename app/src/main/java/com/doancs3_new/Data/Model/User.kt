package com.doancs3_new.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val email: String, // email làm khóa chính
    val firstName: String,
    val lastName: String,
    val nickname: String? = null, // Nullable để cập nhật sau
    val height: Int,
    val currentWeight: Int,
    val targetWeight: Int
)

