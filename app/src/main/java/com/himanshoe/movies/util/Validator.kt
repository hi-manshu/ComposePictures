package com.himanshoe.movies.util

import androidx.compose.ui.text.input.TextFieldValue

fun TextFieldValue.isEmail():Boolean{
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text).matches()
}