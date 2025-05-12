package com.doancs3_new.Data.Model

data class Workout(
    val id: String = "", // Dùng ID của Firestore document
    val title: String = "",
    val description: String = "",
    val videoUrl: String = ""
)
