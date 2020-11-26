package com.himanshoe.landing.di

import com.himanshoe.landing.data.apiHelper.IPhotoRepository
import com.himanshoe.landing.data.apiHelper.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class RepositoryModule {

    @Provides
    fun providePhotoRepository(repository: IPhotoRepository): PhotoRepository {
        return repository
    }
}