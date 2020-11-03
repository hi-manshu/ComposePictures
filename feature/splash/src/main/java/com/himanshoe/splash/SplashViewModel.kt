package com.himanshoe.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshoe.core.navigator.Navigator
import com.himanshoe.splash.SplashDeeplink.deepLinkToLogin
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val navigator = Navigator()

    fun navigate(){
        viewModelScope.launch {
            navigator.navigate(deepLinkToLogin())
        }
    }
}