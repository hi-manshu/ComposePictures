package com.himanshoe.photos.util

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.ResourceFont
import androidx.compose.ui.text.font.fontFamily
import com.himanshoe.photos.R

private val appFontFamily = fontFamily(
    fonts = listOf(
        ResourceFont(
            resId = R.font.nunito_bold,
            weight = FontWeight.W900,
            style = FontStyle.Normal
        ),
        ResourceFont(
            resId = R.font.nunito_regular,
            weight = FontWeight.W700,
            style = FontStyle.Normal
        ),
    )
)
private val defaultTypography = Typography()

