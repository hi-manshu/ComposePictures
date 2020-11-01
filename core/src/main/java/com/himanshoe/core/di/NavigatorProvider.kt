package com.himanshoe.core.di

import com.himanshoe.core.navigator.Navigator
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NavigatorProvider {

    fun provideNavigator(): Navigator {
        return Navigator()
    }

}