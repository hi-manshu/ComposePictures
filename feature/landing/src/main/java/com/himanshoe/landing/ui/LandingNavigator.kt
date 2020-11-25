package com.himanshoe.landing.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.ui.graphics.vector.VectorAsset
import com.himanshoe.landing.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: VectorAsset) {
    object Images : Screen("images", R.string.images, Icons.Filled.Image)
    object Profile : Screen("profile", R.string.profile, Icons.Filled.SupervisedUserCircle)
}

val items = listOf(
    Screen.Images,
    Screen.Profile,
)