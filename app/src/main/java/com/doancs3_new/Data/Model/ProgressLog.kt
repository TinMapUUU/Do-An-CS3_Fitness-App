package com.doancs3_new.Data.Model

data class ProgressLog(
    val uid: String = "",
    val weight: Int = 0,
    val bmi: Double = 0.0,
    val date: String = "", // Timestamp của log
    val day: Int = 0, // Ngày mốc (mỗi 3 ngày)
    val description: String = "" // Mô tả trạng thái BMI (Thiếu cân, Bình thường, Thừa cân, Béo phì)
)
