package com.himanshoe.splash

import androidx.navigation.navOptions
import com.himanshoe.core.navigator.NavigateTo
import com.himanshoe.core.navigator.slideUpAnimation

object SplashDeeplink {
    private val deepLinkToLogin = NavigateTo.DeepLink(
        "compose://login",
        navOptions {
            slideUpAnimation()
        }
    )

    fun deepLinkToLogin() = deepLinkToLogin
}