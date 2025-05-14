package com.doancs3_new.Data.Repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.doancs3_new.Data.Model.Workout
import javax.inject.Inject

class FirestoreRepository @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) {

    fun getWorkoutsByAim(aim: String, callback: (List<Workout>) -> Unit) {
        firebaseFirestore.collection("workouts")
            .whereEqualTo("type", aim)
            .get()
            .addOnSuccessListener { snapshot ->
                val workouts = snapshot.documents.mapNotNull { document ->
                    document.toObject(Workout::class.java)
                }
                callback(workouts)
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Lỗi khi lấy bài tập: ", exception)
            }
    }
}

