package com.doancs3_new.Viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    //Thuộc tính quản lý thông tin người dùng
    var name by mutableStateOf("") // Tên người dùng
        private set

    var age by mutableStateOf(0) // Tuổi người dùng
        private set

    var currentWeight by mutableStateOf(0) // Cân nặng hiện tại
        private set

    var targetWeight by mutableStateOf(0) // Cân nặng mong muốn
        private set

    var heightCm by mutableStateOf(0) // Chiều cao (cm)
        private set

    //Cập nhật thông tin age
    fun updateAge(newAge: Int) {
        age = newAge
    }

    //Cập nhật thông tin name
    fun updateName(newName: String) {
        name = newName
    }

    // Cập nhật cân nặng hiện tại
    fun updateCurrentWeight(weight: Int) {
        currentWeight = weight
    }

    // Cập nhật cân nặng mong muốn
    fun updateTargetWeight(weight: Int) {
        targetWeight = weight
    }

    // Cập nhật chiều cao (cm)
    fun updateHeightCm(height: Int) {
        heightCm = height
    }
}
