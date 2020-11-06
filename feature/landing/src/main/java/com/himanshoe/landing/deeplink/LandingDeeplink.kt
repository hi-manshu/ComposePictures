package com.himanshoe.landing.deeplink

import androidx.navigation.navOptions
import com.himanshoe.core.navigator.NavigateTo
import com.himanshoe.core.navigator.fadeAnimation

object LandingDeeplink {
    private fun linkToEditProfile(source: String) = NavigateTo.DeepLink(
        "compose://edit-profile/$source",
        navOptions {
            fadeAnimation()
        }
    )

    fun deepLinkToEditProfile(source: String) = linkToEditProfile(source)
}