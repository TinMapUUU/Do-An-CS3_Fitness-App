package com.doancs3_new.Data.Model

data class User(
    val uid: String = "",
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    var nickname: String? = null,
    val currentHeight: Int? = 0,
    val currentWeight: Int? = 0,
    val targetWeight: Int? = 0,
    val date: String = "", // millis từ System.currentTimeMillis()
    val currentBMI: Double? = 0.0,
    val targetBMI: Double? = 0.0,
    val aim: String? = null
)

