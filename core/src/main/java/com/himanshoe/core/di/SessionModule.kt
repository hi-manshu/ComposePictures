package com.himanshoe.core.di

import com.himanshoe.core.data.local.session.SessionManager
import com.roomiapp.core.data.local.session.ISessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class SessionModule {
    @Provides

    fun provideSessionManager(sessionManager: ISessionManager): SessionManager {
        return sessionManager
    }

}