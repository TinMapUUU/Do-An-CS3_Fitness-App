package com.doancs3_new.Viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.doancs3_new.Data.Model.Workout
import com.doancs3_new.Data.Repository.FirestoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class WorkoutsViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository,
    private val firebaseFirestore: FirebaseFirestore // Thêm FirebaseFirestore vào constructor
) : ViewModel() {

    private val _workouts = MutableStateFlow<List<Workout>>(emptyList())
    val workouts: StateFlow<List<Workout>> = _workouts

    fun loadWorkouts(aim: String) {
        // Giả sử có một phương thức trong repository để lấy bài tập theo loại
        firestoreRepository.getWorkoutsByAim(aim) { workoutList ->
            Log.d("WorkoutsVM", "Số bài tập tải về: ${workoutList.size}")
            _workouts.value = workoutList
        }
    }
}




