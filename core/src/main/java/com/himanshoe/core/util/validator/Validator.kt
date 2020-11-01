package com.himanshoe.core.util.validator

import androidx.compose.ui.text.input.TextFieldValue


fun TextFieldValue.isEmail():Boolean{
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text).matches()
}

fun isLoginValid(email: String, password: String): List<String> {
    val validator = mutableListOf<String>()
    if (email.isEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        validator.add("Email is not valid")
    }
    if (password.length < 6){
        validator.add("Password is less then 6 characters")
    }
    return validator.toList()
}