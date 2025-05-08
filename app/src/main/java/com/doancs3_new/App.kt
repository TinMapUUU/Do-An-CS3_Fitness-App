package com.doancs3_new

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Các cấu hình khác, ví dụ: Firebase, Analytics, v.v.
    }
}