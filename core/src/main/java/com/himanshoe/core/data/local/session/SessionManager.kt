package com.himanshoe.core.data.local.session

import com.himanshoe.core.model.User

/**
 * Created by Himanshu Singh on 24-10-2020.
 **/
interface SessionManager {
    suspend fun isUserLoggedIn(): Boolean

    suspend fun setUser(user: User)
}