package com.doancs3_new.Data.Respository

import com.doancs3_new.Data.Local.Dao.ProgressDao
import com.doancs3_new.Data.Model.ProgressLog
import jakarta.inject.Inject

class ProgressRepository @Inject constructor(private val dao: ProgressDao) {
    suspend fun insertProgress(log: ProgressLog) = dao.insertLog(log)
    suspend fun getProgress(uid: String) = dao.getLogsForUser(uid)
}
