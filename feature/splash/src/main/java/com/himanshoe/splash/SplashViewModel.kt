package com.himanshoe.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.himanshoe.core.base.IBaseViewModel
import com.himanshoe.core.navigator.Navigator
import com.himanshoe.core.util.NetworkHelper
import com.himanshoe.splash.SplashDeeplink.deepLinkToLogin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor(
    networkHelper: NetworkHelper, val navigator: Navigator
) : IBaseViewModel(networkHelper) {

    fun navigate() {
        viewModelScope.launch {
            delay(3000)
            navigator.navigate(deepLinkToLogin())
        }
    }
}