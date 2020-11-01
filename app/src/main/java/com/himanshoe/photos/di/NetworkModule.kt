package com.himanshoe.photos.di

import com.himanshoe.core.di.NetworkModule.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
class NetworkModule {

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl(): String = "https://picsum.photos/v2/"

}