package com.doancs3_new.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doancs3_new.Data.Model.Workout
import com.doancs3_new.Data.Respository.WorkoutRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@HiltViewModel
class WorkoutsViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : ViewModel() {

    private val _workouts = MutableStateFlow<List<Workout>>(emptyList())
    val workouts: StateFlow<List<Workout>> = _workouts

    // Thêm workout mới vào Firestore
    fun insertWorkout(workouts: List<Workout>) {
        viewModelScope.launch {
            // Gọi phương thức insertWorkout nếu muốn chèn một bài tập đơn
            workoutRepository.insertWorkout(workouts)
            loadWorkouts() // Cập nhật lại danh sách workout sau khi thêm
        }
    }

    // Lấy tất cả workout từ Firestore
    fun loadWorkouts() {
        viewModelScope.launch {
            val allWorkouts = workoutRepository.getAllWorkouts()
            _workouts.value = allWorkouts
        }
    }
}


