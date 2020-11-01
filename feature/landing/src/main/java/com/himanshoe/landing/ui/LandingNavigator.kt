package com.himanshoe.landing.ui

import androidx.annotation.StringRes
import com.himanshoe.landing.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Images : Screen("images", R.string.images)
    object Profile : Screen("profile", R.string.profile)
}

val items = listOf(
    Screen.Images,
    Screen.Profile,
)