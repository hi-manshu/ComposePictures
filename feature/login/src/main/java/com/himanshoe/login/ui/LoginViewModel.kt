package com.himanshoe.login.ui

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    fun doLoginViaEmail(email: String, password: String) {
        val validator = com.himanshoe.core.util.validator.isLoginValid(email, password)
        if (validator.isEmpty()){
            //validated
        }else{
            //showError
        }
    }

}