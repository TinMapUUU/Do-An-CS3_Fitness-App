package com.doancs3_new.Data.Respository

import com.doancs3_new.Data.Local.Dao.WorkoutDao
import com.doancs3_new.Data.Model.Workout
import jakarta.inject.Inject

class WorkoutRepository @Inject constructor(
    private val workoutDao: WorkoutDao
) {
    suspend fun insertWorkout(workout: Workout) = workoutDao.insertWorkout(workout)

    suspend fun getAllWorkouts(): List<Workout> = workoutDao.getAllWorkouts()

    suspend fun updateWorkout(workout: Workout) = workoutDao.updateWorkout(workout)

    suspend fun deleteWorkout(workout: Workout) = workoutDao.deleteWorkout(workout)
}