package com.doancs3_new.Viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.doancs3_new.Data.Model.Workout
import com.doancs3_new.all_UI.Dashboard.WorkoutItem
import com.doancs3_new.all_UI.Dashboard.toWorkoutItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListWorkoutViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _workouts = mutableStateOf<List<WorkoutItem>>(emptyList())
    val workouts: State<List<WorkoutItem>> = _workouts

    init {
        fetchUserWorkouts()
    }

    private fun fetchUserWorkouts() {
        val uid = auth.currentUser?.uid ?: return
        firestore.collection("users").document(uid).get()
            .addOnSuccessListener { userDoc ->
                val aim = userDoc.getString("aim") ?: return@addOnSuccessListener
                firestore.collection("workouts")
                    .whereEqualTo("type", aim) // <-- Sửa tại đây: dùng "type" thay vì "aim"
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        val workoutList = querySnapshot.mapNotNull { doc ->
                            doc.toObject(Workout::class.java).toWorkoutItem()
                        }
                        _workouts.value = workoutList
                    }
                    .addOnFailureListener {
                        Log.e("ListWorkoutViewModel", "Lỗi lấy workouts: ${it.message}")
                    }
            }
            .addOnFailureListener {
                Log.e("ListWorkoutViewModel", "Lỗi lấy aim từ user: ${it.message}")
            }
    }

}
