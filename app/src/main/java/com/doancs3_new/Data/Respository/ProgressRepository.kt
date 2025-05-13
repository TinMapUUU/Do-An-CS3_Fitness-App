package com.doancs3_new.Data.Respository

import com.doancs3_new.Data.Model.ProgressLog
import com.doancs3_new.Data.Model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class ProgressRepository @Inject constructor() {

    private val firestore = FirebaseFirestore.getInstance()
    private val progressCollection = firestore.collection("progress_logs")
    private val userCollection = firestore.collection("users")

    suspend fun insertProgress(log: ProgressLog) {
        progressCollection.add(log)
    }

    suspend fun getProgress(uid: String): List<ProgressLog> {
        return progressCollection
            .whereEqualTo("uid", uid)
            .orderBy("date", Query.Direction.DESCENDING)
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(ProgressLog::class.java) }
    }

    suspend fun getLastProgress(uid: String): ProgressLog? {
        return progressCollection
            .whereEqualTo("uid", uid)
            .orderBy("date", Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .await()
            .documents
            .firstOrNull()
            ?.toObject(ProgressLog::class.java)
    }

    suspend fun getUserById(uid: String): User? {
        return userCollection
            .document(uid)
            .get()
            .await()
            .toObject(User::class.java)
    }
}

