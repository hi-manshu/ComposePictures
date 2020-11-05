package com.himanshoe.core.data.local.session

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.himanshoe.core.data.local.datastore.store.AppConfig
import com.himanshoe.core.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Himanshu Singh on 24-10-2020.
 **/
class ISessionManager @Inject constructor() : SessionManager {

    override suspend fun isUserLoggedIn(): Boolean {
        return withContext(Dispatchers.IO) {
            val data = AppConfig.getSync(AppConfig.Keys.USER, AppConfig.DEFAULT.STRING)
            return@withContext Gson().fromJson(data, User::class.java).isLoggedIn()
        }
    }

    override suspend fun setUser(user: User) {
        AppConfig.setUser(user)
    }


    override suspend fun logout() {
        Firebase.auth.signOut()
        setUser(
            User(
                id = AppConfig.DEFAULT.STRING,
                description = AppConfig.DEFAULT.STRING,
                displayUrl = AppConfig.DEFAULT.STRING,
                name = AppConfig.DEFAULT.STRING,
                email = AppConfig.DEFAULT.STRING
            )
        )
    }

    override suspend fun getUser(): Flow<User> {
        return AppConfig.getUser()
    }

}