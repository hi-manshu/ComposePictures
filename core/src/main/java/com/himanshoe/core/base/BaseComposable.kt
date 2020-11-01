package com.himanshoe.core.base

import android.content.Context
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.himanshoe.core.util.appTypography

fun PhotoCollectorTheme(context: Context, content: @Composable () -> Unit): ComposeView {
    return ComposeView(context).apply {
        setContent {
            MaterialTheme(typography = appTypography) {
                content()
            }
        }
    }
}