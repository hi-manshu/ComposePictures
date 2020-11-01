package com.himanshoe.photos

import android.app.Application
import com.google.firebase.FirebaseApp
import com.himanshoe.core.data.local.datastore.PreferenceInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PhotoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceInitializer.init(this)
    }
}