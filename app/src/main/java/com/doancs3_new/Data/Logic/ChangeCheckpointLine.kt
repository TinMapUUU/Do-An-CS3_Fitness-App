package com.doancs3_new.Data.Logic

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class ChangeCheckpointLine(private val firestore: FirebaseFirestore) {

    fun updateCheckpointProgress(
        uid: String,
        checkpointIndex: Int
    ) {
        val userRef = firestore.collection("users").document(uid)

        userRef.get().addOnSuccessListener { doc ->
            val currentWeight = doc.getDouble("currentWeight") ?: return@addOnSuccessListener
            val targetWeight = doc.getDouble("targetWeight") ?: return@addOnSuccessListener

            // Tính số checkpoint còn lại (tổng: 8 cho 24 ngày)
            val totalCheckpoints = 8
            val stepChange = (targetWeight - currentWeight) / (totalCheckpoints - checkpointIndex)

            val updatedWeight = currentWeight + stepChange

            // Cập nhật lại weight mới vào user
            userRef.update("currentWeight", updatedWeight)

            // Format ngày cho progressLogs
            val dateFormat = SimpleDateFormat("dd/MM - HH:mm", Locale.getDefault())
            val currentDate = dateFormat.format(Date())

            // Tạo log tiến độ mới
            val log = hashMapOf(
                "uid" to uid,
                "weight" to updatedWeight,
                "date" to currentDate
            )

            firestore.collection("progressLogs")
                .add(log)
                .addOnSuccessListener {
                    Log.d(
                        "Checkpoint",
                        "Đã cập nhật log tại checkpoint $checkpointIndex: $updatedWeight kg"
                    )
                }
                .addOnFailureListener {
                    Log.e("Checkpoint", "Lỗi khi cập nhật progress log", it)
                }

        }.addOnFailureListener {
            Log.e("Checkpoint", "Lỗi khi lấy thông tin người dùng", it)
        }
    }
}
