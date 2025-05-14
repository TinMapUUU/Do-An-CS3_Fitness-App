package com.doancs3_new.Viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.doancs3_new.Data.Model.User
import com.doancs3_new.Data.Repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.doancs3_new.Data.Model.Workout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val userRepo: UserRepository // Sử dụng Firebase repository
) : ViewModel() {

    var firstName by mutableStateOf("")
        private set

    var lastName by mutableStateOf("")
        private set
    var nickname by mutableStateOf("")
    var email by mutableStateOf("")

    var currentWeightInput by mutableStateOf("")
    var targetWeightInput by mutableStateOf("")
    var heightInput by mutableStateOf("")

    val currentWeight: Int? get() = currentWeightInput.toIntOrNull()
    val targetWeight: Int? get() = targetWeightInput.toIntOrNull()
    val currentHeight: Int? get() = heightInput.toIntOrNull()

    private val _selectedAim = MutableStateFlow<String?>(null)
    val selectedAim: StateFlow<String?> = _selectedAim

    fun setSelectedAim(aim: String) {
        _selectedAim.value = aim
    }

    // Mục tiêu người dùng: giảm cân / tăng cân / giữ dáng
    var userAim by mutableStateOf("")
        private set

    fun updateUserAim(aim: String) {
        userAim = aim
    }

    // Danh sách bài tập
    var workouts by mutableStateOf<List<Workout>>(emptyList())
        private set


    fun updateWorkouts(newList: List<Workout>) {
        workouts = newList
    }


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

    // Lưu thông tin vào Firebase
    suspend fun saveUserProfile() {
        val user = User(
            email = email,
            firstName = firstName,
            lastName = lastName,
            nickname = nickname,
            currentWeight = currentWeight ?: 0,
            currentHeight = currentHeight ?: 0,
            targetWeight = targetWeight ?: 0,
            currentBMI = 0.0, // Tính BMI sau
            targetBMI = 0.0, // Tính BMI mong muốn
            date = "" // Có thể cập nhật sau
        )
        userRepo.saveUser(user) // Lưu vào Firebase
    }
}
