package com.himanshoe.splash.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.himanshoe.core.common.CoilImage
import com.himanshoe.core.extension.fullScreen
import com.himanshoe.splash.R

@Composable
fun SplashUI() {

    Box(modifier = Modifier.fullScreen(backgroundColor = Color.Black)) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            CoilImage(
                R.drawable.splash_image,
                modifier = Modifier.height(80.dp).width(160.dp)
            )
            CircularProgressIndicator(
                color = Color.White, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp)
            )

        }
    }
}

