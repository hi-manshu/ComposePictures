package com.himanshoe.core.data.local.datastore.store

import androidx.datastore.preferences.preferencesKey
import com.google.gson.Gson
import com.himanshoe.core.data.local.datastore.DataStoreProvider
import com.himanshoe.core.data.local.datastore.store.AppConfig.DEFAULT.STRING
import com.himanshoe.core.data.local.datastore.store.AppConfig.Keys.USER
import com.himanshoe.core.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Himanshu Singh on 23-10-2020.
 **/

object AppConfig : DataStoreProvider() {

    override fun prefName() = "picture_pref_core"

    fun getUser(): Flow<User> {
        return getValue(USER).map {
            Gson().fromJson(it, User::class.java)
        }
    }

    suspend fun logout() {
        setUser(
            User(
                id = STRING,
                description = STRING,
                displayUrl = STRING,
                name = STRING,
                email = STRING
            )
        )
    }

    suspend fun setUser(user: User) {
        setValue(USER, Gson().toJson(user))
    }


    object Keys {
        private const val userKey = "user"
        val USER = preferencesKey<String>(userKey)

    }

    object DEFAULT {
        const val STRING = ""
    }
}
