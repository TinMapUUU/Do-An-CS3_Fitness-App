package com.doancs3_new.Viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.doancs3_new.Data.Local.Dao.UserDao
import com.doancs3_new.Data.Model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    var name by mutableStateOf("")
        private set

    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var nickname by mutableStateOf("")
    var email by mutableStateOf("")

    // Nhập liệu dạng chuỗi (hiển thị trong TextField)
    var currentWeightInput by mutableStateOf("")
    var targetWeightInput by mutableStateOf("")
    var heightInput by mutableStateOf("")

    // Dùng để tính toán hoặc lưu trữ dữ liệu sạch
    val currentWeight: Int? get() = currentWeightInput.toIntOrNull()
    val targetWeight: Int? get() = targetWeightInput.toIntOrNull()
    val heightCm: Int? get() = heightInput.toIntOrNull()

    // Cập nhật dữ liệu nhập
    fun updateFirstName(newFirstName: String) {
        firstName = newFirstName
    }

    fun updateLastName(newLastName: String) {
        lastName = newLastName
    }

    fun updateNickname(newNickname: String) {
        nickname = newNickname
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

    // Lưu thông tin vào database
    suspend fun saveUserProfile(userDao: UserDao) {
        val user = User(
            email = email,
            firstName = firstName,
            lastName = lastName,
            nickname = nickname, // nickname sau khi nhập
            height = heightCm ?: 0,
            currentWeight = currentWeight ?: 0,
            targetWeight = targetWeight ?: 0
        )
        userDao.insertUser(user) // Insert user vào database
    }

    suspend fun updateNicknameInDatabase(userDao: UserDao, newNickname: String) {
        userDao.updateNickname(email, newNickname)
    }
}



