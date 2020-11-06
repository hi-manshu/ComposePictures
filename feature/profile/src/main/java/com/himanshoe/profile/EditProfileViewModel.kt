package com.himanshoe.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.himanshoe.core.base.IBaseViewModel
import com.himanshoe.core.data.local.session.SessionManager
import com.himanshoe.core.model.User
import com.himanshoe.core.navigator.NavigateTo
import com.himanshoe.core.navigator.Navigator
import com.himanshoe.core.util.IResult
import com.himanshoe.core.util.NetworkHelper
import com.himanshoe.core.util.validator.isUserDataValid
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EditProfileViewModel @ViewModelInject constructor(
    val navigator: Navigator,
    networkHelper: NetworkHelper,
    private val sessionManager: SessionManager
) : IBaseViewModel(networkHelper) {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _userSave = MutableLiveData<IResult<String>>()
    val userSave: LiveData<IResult<String>>
        get() = _userSave

    fun init() {
        viewModelScope.launch {
            sessionManager.getUser()
                .collect {
                    _user.postValue(it)
                }
        }
    }

    fun saveUser(name: String, email: String, description: String) {
        viewModelScope.launch {
            val validator = isUserDataValid(name, email, description)
            if (validator.isEmpty()) {
                user.value?.copy(name = name, email = email, description = description)
                    ?.let { sessionManager.setUser(it) }
                _userSave.postValue(IResult.OnSuccess("User data updated"))
            } else {
                _userSave.postValue(IResult.OnFailed(Throwable(validator.first())))
            }
        }
    }

    fun navigateBack() {
        navigator.navigate(NavigateTo.Back)
    }
}