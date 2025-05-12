package com.doancs3_new.Data.Respository

import com.doancs3_new.Data.Model.ProgressLog
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class ProgressRepository @Inject constructor() {
    private val firestore = FirebaseFirestore.getInstance()
    private val progressCollection = firestore.collection("progress_logs")

    suspend fun insertProgress(log: ProgressLog) {
        progressCollection.add(log) // Add a new log to Firestore
    }

    suspend fun getProgress(uid: String): List<ProgressLog> {
        return progressCollection.whereEqualTo("userId", uid).get()
            .await() // Using Kotlin Coroutines with Firestore
            .documents
            .map { it.toObject(ProgressLog::class.java)!! }
    }
}
