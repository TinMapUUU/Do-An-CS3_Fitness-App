package com.doancs3_new.Data.Model

data class ProgressLog(
    val uid: String = "", // Dùng ID Firestore, hoặc rỗng nếu auto
    val date: Long = 0L,
    val weight: Double? = null
)
