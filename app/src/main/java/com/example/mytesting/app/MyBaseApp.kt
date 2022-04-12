package com.example.mytesting.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyBaseApp: Application() {
    companion object{
        var showLogs: Boolean = true
    }
}