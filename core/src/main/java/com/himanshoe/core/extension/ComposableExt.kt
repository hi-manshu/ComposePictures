package com.himanshoe.core.extension

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

fun Modifier.fullScreen(backgroundColor :Color = Color.White):Modifier{
   return this.fillMaxWidth().fillMaxHeight().background(backgroundColor)
}