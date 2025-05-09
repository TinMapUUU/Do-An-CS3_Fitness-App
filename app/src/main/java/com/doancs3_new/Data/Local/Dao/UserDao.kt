package com.doancs3_new.Data.Local.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.doancs3_new.Data.Model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Update
    suspend fun updateUser(user: User)

    @Query("UPDATE users SET nickname = :nickname WHERE email = :email")
    suspend fun updateNickname(email: String, nickname: String)

    @Delete
    suspend fun deleteUser(user: User)
}

