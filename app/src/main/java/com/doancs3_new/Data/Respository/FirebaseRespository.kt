package com.doancs3_new.Data.Repository

import com.google.firebase.firestore.FirebaseFirestore
import com.doancs3_new.Data.Model.User
import javax.inject.Inject

class FirebaseRepository @Inject constructor() {

    private val db = FirebaseFirestore.getInstance()
    private val userRef = db.collection("users") // Sử dụng Firestore

    // Thêm hoặc cập nhật người dùng
    fun saveUser(user: User) {
        userRef.document(user.email).set(user)
    }

    // Lấy thông tin người dùng
    fun getUser(email: String, callback: (User?) -> Unit) {
        userRef.document(email).get().addOnSuccessListener {
            val user = it.toObject(User::class.java)
            callback(user)
        }
    }
}
