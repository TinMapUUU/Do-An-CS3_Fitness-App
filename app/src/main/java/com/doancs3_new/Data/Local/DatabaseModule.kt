package com.doancs3_new.Data.Local

import android.content.Context
import androidx.room.Room
import com.doancs3_new.Data.Local.Dao.ProgressDao
import com.doancs3_new.Data.Local.Dao.UserDao
import com.doancs3_new.Data.Local.Dao.WorkoutDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
//import jakarta.inject.Singleton
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "fitness_app.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideProgressDao(db: AppDatabase): ProgressDao = db.progressDao()

    @Provides
    @Singleton
    fun provideWorkoutDao(db: AppDatabase): WorkoutDao = db.workoutDao()
}

