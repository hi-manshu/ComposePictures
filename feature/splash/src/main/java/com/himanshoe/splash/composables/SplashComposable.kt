package com.himanshoe.splash.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun SplashUI() {
    val colorState = remember { mutableStateOf(LogoState.IDLE) }


}

