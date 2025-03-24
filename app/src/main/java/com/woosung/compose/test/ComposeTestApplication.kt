package com.woosung.compose.test

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComposeTestApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}
