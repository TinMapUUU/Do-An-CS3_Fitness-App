package com.doancs3_new.Viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.doancs3_new.Data.Model.Workout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@HiltViewModel
class ProgressLogViewModel @Inject constructor() : ViewModel() {

    private val _progressLogs = MutableStateFlow<List<ProgressEntry>>(emptyList())
    val progressLogs: StateFlow<List<ProgressEntry>> = _progressLogs.asStateFlow()

    private val _workouts = MutableStateFlow<List<Workout>>(emptyList())
    val workouts: StateFlow<List<Workout>> = _workouts.asStateFlow()


    private var listenerRegistration: ListenerRegistration? = null

    fun startListeningProgressLogs(uid: String) {
        val db = FirebaseFirestore.getInstance()
        listenerRegistration?.remove() // tránh leak nếu gọi lại

        listenerRegistration = db.collection("progressLogs")
            .whereEqualTo("uid", uid)
            .orderBy("date", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshots, error ->
                if (error != null) {
                    Log.e("ProgressLogViewModel", "Lắng nghe fail: ", error)
                    return@addSnapshotListener
                }

                val logs = snapshots?.documents?.mapNotNull { doc ->
                    val weight = doc.getDouble("weight")
                    val formattedDate = doc.getString("date")  // Lấy date dưới dạng String

                    if (weight != null && formattedDate != null) {
                        ProgressEntry(weight.toFloat(), formattedDate)
                    } else null
                } ?: emptyList()

                _progressLogs.value = logs
            }
    }

    private val db = FirebaseFirestore.getInstance()
    private val currentDate = Date()

    // Định dạng ngày tháng theo kiểu "dd/MM - HH:mm"
    private val dateFormat = SimpleDateFormat("dd/MM - HH:mm", Locale.getDefault())
    val formattedDate: String = dateFormat.format(currentDate)

    fun addProgressLog(uid: String, weight: Double) {
        val log = hashMapOf(
            "uid" to uid,
            "weight" to weight,
            "date" to formattedDate
        )

        db.collection("progressLogs")
            .add(log)
            .addOnSuccessListener {
                Log.d("ProgressLogViewModel", "Progress log thêm thành công")
            }
            .addOnFailureListener { e ->
                Log.e("ProgressLogViewModel", "Lỗi thêm progress log", e)
            }
    }


    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }
}

data class ProgressEntry(
    val weight: Float,
    val date: String = ""
)


