package com.doancs3_new.Data.Model

data class ProgressLog(
    val id: String = "", // Dùng ID Firestore, hoặc rỗng nếu auto
    val userId: String = "", // email hoặc UID
    val date: String = "",   // Ví dụ: "2025-05-11"
    val weight: Int = 0
)
