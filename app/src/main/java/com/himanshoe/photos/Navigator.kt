package com.himanshoe.photos

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object  Movies: Screen("movies", R.string.movies)
    object Profile : Screen("profile", R.string.profile)
}

val items = listOf(
    Screen.Movies,
    Screen.Profile,
)