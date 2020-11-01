package com.himanshoe.core.data.local.datastore

/**
 * Created by Himanshu Singh on 23-10-2020.
 **/
import android.content.Context
import com.himanshoe.core.data.local.datastore.store.AppConfig

object PreferenceInitializer {

    private val preferences = listOf(AppConfig)

    fun init(context: Context) {
        preferences.forEach { it.init(context) }
    }
}
