package com.doancs3_new.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doancs3_new.Data.Model.ProgressLog
import com.doancs3_new.Data.Respository.ProgressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val repo: ProgressRepository
) : ViewModel() {

    private val _logs = MutableStateFlow<List<ProgressLog>>(emptyList())
    val logs: StateFlow<List<ProgressLog>> = _logs

    fun loadLogs(userId: String) {
        viewModelScope.launch {
            _logs.value = repo.getProgress(userId)
        }
    }
}
