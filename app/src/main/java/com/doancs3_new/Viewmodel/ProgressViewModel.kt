package com.doancs3_new.Viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doancs3_new.Data.Model.ProgressLog
import com.doancs3_new.Data.Respository.ProgressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val repo: ProgressRepository
) : ViewModel() {

    private val _logs = MutableStateFlow<List<ProgressLog>>(emptyList())
    val logs: StateFlow<List<ProgressLog>> = _logs

    fun loadLogs(userId: String) {
        viewModelScope.launch {
            _logs.value = repo.getProgress(userId) // Sử dụng Firebase repository
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertTestLog() {
        viewModelScope.launch {
            val dummy = ProgressLog(
                userId = "test_user",
                date = LocalDate.now().toString(),
                weight = 60
            )
            repo.insertProgress(dummy) // Sử dụng Firebase repository
            loadLogs("test_user") // Tải lại logs sau khi thêm
        }
    }
}
