package com.himanshoe.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.himanshoe.core.base.IBaseViewModel
import com.himanshoe.core.data.local.session.SessionManager
import com.himanshoe.core.navigator.Navigator
import com.himanshoe.core.util.NetworkHelper
import com.himanshoe.splash.SplashDeeplink.deepLinkToLanding
import com.himanshoe.splash.SplashDeeplink.deepLinkToLogin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor(
    networkHelper: NetworkHelper,
    val navigator: Navigator,
    private val sessionManager: SessionManager
) : IBaseViewModel(networkHelper) {

    fun navigate() {
        viewModelScope.launch {
            sessionManager.getUser().collect { user ->
                delay(3000)
                if (user !=null && user.isLoggedIn()) {
                    navigator.navigate(deepLinkToLanding())
                } else {
                    navigator.navigate(deepLinkToLogin())
                }
            }
        }
    }

}