package com.himanshoe.login.ui

import androidx.hilt.lifecycle.ViewModelInject
import com.himanshoe.core.base.IBaseViewModel
import com.roomiapp.core.util.NetworkHelper

class LoginViewModel @ViewModelInject constructor(
    networkHelper: NetworkHelper
) : IBaseViewModel(networkHelper) {
    fun doLoginViaEmail(email: String, password: String) {
        val validator = com.himanshoe.core.util.validator.isLoginValid(email, password)
        if (validator.isEmpty()) {
            //validated
        } else {
            //showError
        }
    }
}

