package com.doancs3_new.Data.Respository

import com.doancs3_new.Data.Local.Dao.UserDao
import com.doancs3_new.Data.Model.User
import jakarta.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun getUserById(uid: String): User? = userDao.getUserByEmail(uid)

    suspend fun updateUser(user: User) = userDao.updateUser(user)

    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
}
