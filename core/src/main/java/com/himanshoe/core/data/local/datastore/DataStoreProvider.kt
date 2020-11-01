package com.himanshoe.core.data.local.datastore

/**
 * Created by Himanshu Singh on 23-10-2020.
 **/
import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

abstract class DataStoreProvider : IDataStoreProvider {

    private lateinit var dataStore: DataStore<Preferences>

    fun init(context: Context): DataStore<Preferences> {
        if (!::dataStore.isInitialized) {
            dataStore = context.applicationContext.createDataStore(prefName())
        }
        return dataStore
    }

    fun <T> getValue(key: Preferences.Key<T>, DEFAULT: T): Flow<T> {
        return dataStore.data.catch { emit(emptyPreferences()) }
            .map { preference -> preference[key] ?: DEFAULT }
    }

    fun <T> getValue(key: Preferences.Key<T>): Flow<T?> {
        return dataStore.data.catch { emit(emptyPreferences()) }
            .map { preference -> preference[key] }
    }

    suspend fun <T> getSync(key: Preferences.Key<T>): T? {
        return dataStore.data.firstOrNull()?.get(key)
    }

    suspend fun <T> getSync(key: Preferences.Key<T>, default: T): T {
        return getSync(key) ?: default
    }

    suspend fun <T> setValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}
