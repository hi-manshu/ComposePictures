package com.roomiapp.core.data.local.session

import com.himanshoe.core.data.local.datastore.store.AppConfig
import com.himanshoe.core.data.local.session.SessionManager
import com.himanshoe.core.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Himanshu Singh on 24-10-2020.
 **/
class ISessionManager @Inject constructor() : SessionManager {

    override suspend fun isUserLoggedIn(): Boolean {
        return withContext(Dispatchers.IO) {
            val data = AppConfig.getSync(AppConfig.Keys.USER, AppConfig.DEFAULT.STRING)
            return@withContext data.isNotEmpty()
        }
    }

    override suspend fun setUser(user: User) {
        AppConfig.setUser(user)
    }

}