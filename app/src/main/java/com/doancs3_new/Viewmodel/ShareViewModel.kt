package com.doancs3_new.Viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    var name by mutableStateOf("")
        private set

    var age by mutableStateOf(0)
        private set

    // Nhập liệu dạng chuỗi (hiển thị trong TextField)
    var currentWeightInput by mutableStateOf("")
    var targetWeightInput by mutableStateOf("")
    var heightInput by mutableStateOf("")

    // Dùng để tính toán hoặc lưu trữ dữ liệu sạch
    val currentWeight: Int? get() = currentWeightInput.toIntOrNull()
    val targetWeight: Int? get() = targetWeightInput.toIntOrNull()
    val heightCm: Int? get() = heightInput.toIntOrNull()

    // Cập nhật dữ liệu nhập
    fun updateName(newName: String) {
        name = newName
    }

    fun updateAge(newAge: Int) {
        age = newAge
    }

    fun updateCurrentWeightInput(input: String) {
        currentWeightInput = input
    }

    fun updateTargetWeightInput(input: String) {
        targetWeightInput = input
    }

    fun updateHeightInput(input: String) {
        heightInput = input
    }
}



