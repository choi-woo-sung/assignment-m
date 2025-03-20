package com.woosung.compose.test

import android.app.Application
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComposeTestApplication : Application(){
    override fun onCreate() {
        Mavericks.initialize(this)
        super.onCreate()
    }
}