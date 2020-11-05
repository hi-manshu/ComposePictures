package com.himanshoe.login.ui

import androidx.navigation.navOptions
import com.himanshoe.core.navigator.NavigateTo
import com.himanshoe.core.navigator.fadeAnimation

object LoginDeeplink {

    private val deepLinkToLanding = NavigateTo.DeepLink(
        "compose://landing",
        navOptions {
            fadeAnimation()
        }
    )

    fun deepLinkToLanding() = deepLinkToLanding
}