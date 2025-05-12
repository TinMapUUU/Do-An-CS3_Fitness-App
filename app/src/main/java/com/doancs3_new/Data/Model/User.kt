package com.doancs3_new.Data.Model

data class User(
    val uid: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    var nickname: String? = null,
    val currentHeight: Int = 0,
    val currentWeight: Int = 0,
    val targetWeight: Int = 0,
    val date: String = "", // Ví dụ "11/05"
    val currentBMI: Double = 0.0,
    val targetBMI: Double = 0.0
)

