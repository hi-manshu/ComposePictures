package com.himanshoe.login.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.himanshoe.core.base.IBaseViewModel
import com.himanshoe.core.data.local.session.SessionManager
import com.himanshoe.core.model.User
import com.himanshoe.core.navigator.Navigator
import com.himanshoe.core.util.validator.isLoginValid
import com.himanshoe.core.util.IResult
import com.himanshoe.core.util.NetworkHelper
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    networkHelper: NetworkHelper,
    val navigator: Navigator,
    private val sessionManager: SessionManager
) : IBaseViewModel(networkHelper) {

    private val _initialize = MutableLiveData<Unit>()
    val initialize: LiveData<Unit>
        get() = _initialize
    private val _loginViaGoogle = MutableLiveData<Unit>()
    val loginViaGoogle: LiveData<Unit>
        get() = _loginViaGoogle

    private val _emailAndPassword = MutableLiveData<IResult<Pair<String, String>>>()
    val emailAndPassword: LiveData<IResult<Pair<String, String>>>
        get() = _emailAndPassword

    private val _idToken = MutableLiveData<IResult<String>>()
    val idToken: LiveData<IResult<String>>
        get() = _idToken

    fun doLoginViaEmail(email: String, password: String) {
        val validator = isLoginValid(email, password)
        if (validator.isEmpty()) {
            _emailAndPassword.postValue(IResult.OnSuccess(Pair(email, password)))
        } else {
            _emailAndPassword.postValue(IResult.OnFailed(Throwable(validator.first())))
        }
    }

    fun init() {
        _initialize.postValue(Unit)
    }

    fun setUser(user: FirebaseUser?) {
        viewModelScope.launch {
            if (user != null) {
                sessionManager.setUser(
                    User(
                        id = user.uid,
                        displayUrl = user.photoUrl.toString(),
                        name = user.displayName ?: "",
                        email = user.email ?: ""
                    )
                )
            }
        }
    }

    fun getUserFromGoogleId(idToken: String?) {
        if (idToken != null) {
            _idToken.postValue(IResult.OnSuccess(idToken))
        } else {
            _idToken.postValue(IResult.OnFailed(Throwable("Token is null")))
        }
    }

    fun doLoginViewGoogle() {
        _loginViaGoogle.postValue(Unit)
    }

}

