package com.doancs3_new.Viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doancs3_new.Data.Model.User
import com.doancs3_new.Data.Repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    fun saveUser(user: User) {
        viewModelScope.launch {
            userRepository.saveUser(user)
        }
    }

    fun updateNickname(uid: String, nickname: String) {
        val userRef = FirebaseFirestore.getInstance().collection("users").document(uid)
        userRef.set(mapOf("nickname" to nickname), SetOptions.merge())
            .addOnSuccessListener {
                Log.d("UserViewModel", "Thêm nick name thành công")
            }
            .addOnFailureListener { e ->
                Log.e("UserViewModel", "Lỗi thêm nick name", e)
            }
    }

    fun loadUser(email: String) {
        viewModelScope.launch {
            val fetchedUser = userRepository.getUserByEmail(email)
            _user.value = fetchedUser
        }
    }

    fun updateProfile(
        uid: String,
        nickname: String,
        currentHeight: Int,
        currentWeight: Int,
        targetWeight: Int
    ) {
        val userRef = FirebaseFirestore.getInstance().collection("users").document(uid)

        val updates = mapOf(
            "nickname" to nickname,
            "currentHeight" to currentHeight,
            "currentWeight" to currentWeight,
            "targetWeight" to targetWeight
        )

        userRef.set(updates, SetOptions.merge())
            .addOnSuccessListener {
                Log.d("UserViewModel", "cập nhật profile thành công")
            }
            .addOnFailureListener { e ->
                Log.e("UserViewModel", "Lỗi cập nhật profile", e)
            }
    }

    fun calculateBMI(currentWeight: Int, heightCm: Int): Double {
        val heightM = heightCm / 100.0
        return (currentWeight / (heightM * heightM * 1.0))
    }

    fun loadUserDataFromFirebase(uid: String) {
        val userRef = FirebaseFirestore.getInstance().collection("users").document(uid)
        userRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val currentWeight = document.getLong("currentWeight")?.toInt() ?: 0
                    val heightCm = document.getLong("currentHeight")?.toInt() ?: 0
                    val targetWeight = document.getLong("targetWeight")?.toInt() ?: 0

                    val currentBMI = calculateBMI(currentWeight, heightCm)

                    Log.d("UserViewModel", "BMI = $currentBMI")

                    // Lưu vào _user nếu bạn cần dùng Composable để quan sát
                    _user.value = User(
                        email = document.getString("email") ?: "",
                        nickname = document.getString("nickname") ?: "",
                        currentWeight = currentWeight,
                        currentHeight = heightCm,
                        targetWeight = targetWeight,
                        currentBMI = currentBMI // thêm nếu bạn có trường này trong model
                    )
                }
            }
            .addOnFailureListener {
                Log.e("UserViewModel", "Lỗi khi lấy dữ liệu người dùng", it)
            }
    }

}

