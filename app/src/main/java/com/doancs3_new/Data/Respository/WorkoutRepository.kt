package com.doancs3_new.Data.Respository

import com.doancs3_new.Data.Model.Workout
import com.google.firebase.firestore.FirebaseFirestore
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class WorkoutRepository @Inject constructor() {
    private val firestore = FirebaseFirestore.getInstance()
    private val workoutsCollection = firestore.collection("workouts")

    suspend fun insertWorkout(workout: Workout) {
        workoutsCollection.add(workout) // Add new workout to Firestore
    }

    suspend fun getAllWorkouts(): List<Workout> {
        return workoutsCollection.get().await() // Get all workouts from Firestore
            .documents
            .map { it.toObject(Workout::class.java)!! }
    }

    suspend fun updateWorkout(workout: Workout) {
        workoutsCollection.document(workout.id).set(workout) // Update a workout in Firestore
    }

    suspend fun deleteWorkout(workout: Workout) {
        workoutsCollection.document(workout.id).delete() // Delete workout from Firestore
    }
}
