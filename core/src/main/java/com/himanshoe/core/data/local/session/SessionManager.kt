package com.himanshoe.core.data.local.session

/**
 * Created by Himanshu Singh on 24-10-2020.
 **/
interface SessionManager {
    suspend fun isUserLoggedIn(): Boolean
}