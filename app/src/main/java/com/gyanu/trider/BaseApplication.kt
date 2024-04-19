package com.gyanu.trider

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.compose.runtime.MutableState
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()


    }}

