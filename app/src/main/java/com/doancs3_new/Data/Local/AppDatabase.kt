package com.doancs3_new.Data.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.doancs3_new.Data.Local.Dao.ProgressDao
import com.doancs3_new.Data.Local.Dao.UserDao
import com.doancs3_new.Data.Local.Dao.WorkoutDao
import com.doancs3_new.Data.Model.ProgressLog
import com.doancs3_new.Data.Model.User
import com.doancs3_new.Data.Model.Workout

@Database(entities = [User::class, Workout::class, ProgressLog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun progressDao(): ProgressDao
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
}