package com.doancs3_new.Data.Respository

import com.doancs3_new.Data.Model.Workout
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WorkoutRepository @Inject constructor() {
    private val firestore = FirebaseFirestore.getInstance()
    private val workoutsCollection = firestore.collection("workouts")
//    suspend fun insertWorkout(workouts: List<Workout>) {
//        workouts.forEach { workout ->
//            workoutsCollection.add(workout) // Add new workout to Firestore
//        }
//    }

    suspend fun getAllWorkouts(): List<Workout> {
        val snapshot = workoutsCollection.get().await()
        val workoutList = snapshot.documents.mapNotNull { doc ->
            val workout = doc.toObject(Workout::class.java)
            println("🔥 Workout từ Firestore: $workout")
            workout
        }
        println("✅ Tổng số workout lấy ra: ${workoutList.size}")
        return workoutList
    }

    suspend fun insertOrUpdateWorkout(workout: Workout) {
        val docRef = Firebase.firestore.collection("workouts").document(workout.id)
        docRef.set(workout) // nếu trùng id thì sẽ update, chưa có thì thêm mới
    }


}
