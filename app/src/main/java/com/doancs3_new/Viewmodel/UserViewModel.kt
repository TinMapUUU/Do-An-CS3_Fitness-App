package com.doancs3_new.Viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doancs3_new.Data.Local.Dao.UserDao
import com.doancs3_new.Data.Model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    // Thay vì dùng Thread.State, sử dụng State từ Jetpack Compose để quản lý dữ liệu
    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user // sửa lại kiểu từ Thread.State<User?> thành State<User?>

    fun saveUser(user: User) {
        viewModelScope.launch {
            userDao.insertUser(user)
        }
    }

    fun loadUser(userId: String) {
        viewModelScope.launch {
            _user.value = userDao.getUserByEmail(userId)
        }
    }

    fun updateNickname(email: String, nickname: String) {
        viewModelScope.launch {
            userDao.updateNickname(email, nickname)
        }
    }

    fun saveUserFromShared(shared: SharedViewModel) {
        viewModelScope.launch {
            val user = User(
                email = shared.email,
                firstName = shared.firstName,
                lastName = shared.lastName,
                nickname = shared.nickname,
                height = shared.heightCm ?: 0,
                currentWeight = shared.currentWeight ?: 0,
                targetWeight = shared.targetWeight ?: 0
            )
            userDao.insertUser(user)
        }
    }



}
