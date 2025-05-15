package com.doancs3_new.Data.Repository

import com.doancs3_new.Data.Model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository @Inject constructor() {
    private val firestore = FirebaseFirestore.getInstance()
    private val usersCollection = firestore.collection("users")

    // Thêm hoặc cập nhật người dùng (sử dụng email làm ID nếu muốn)
//    suspend fun saveUser(user: User) {
//        usersCollection.document(user.email).set(user).await()
//    }

    // Lấy người dùng theo email
    suspend fun getUserByEmail(email: String): User? {
        return try {
            val querySnapshot = usersCollection
                .whereEqualTo("email", email)
                .limit(1)
                .get()
                .await()
            val doc = querySnapshot.documents.firstOrNull()
            doc?.toObject(User::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateUser(email: String, user: User) {
        usersCollection.document(email).set(user).await()
    }

    suspend fun deleteUser(email: String) {
        usersCollection.document(email).delete().await()
    }
}
