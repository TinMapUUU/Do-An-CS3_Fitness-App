package com.doancs3_new.Data.Local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.doancs3_new.Data.Model.ProgressLog

@Dao
interface ProgressDao {
    @Insert
    suspend fun insertLog(log: ProgressLog)

    @Query("SELECT * FROM progress_logs WHERE userId = :uid ORDER BY date ASC")
    suspend fun getLogsForUser(uid: String): List<ProgressLog>
}