package com.himanshoe.landing.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.himanshoe.core.base.IBaseViewModel
import com.himanshoe.core.data.local.session.SessionManager
import com.himanshoe.core.model.Source
import com.himanshoe.core.model.User
import com.himanshoe.core.navigator.NavigateTo
import com.himanshoe.core.navigator.Navigator
import com.himanshoe.core.util.NetworkHelper
import com.himanshoe.landing.data.response.PhotoResponse
import com.himanshoe.landing.deeplink.LandingDeeplink.deepLinkToEditProfile
import com.himanshoe.landing.domain.GetPhotosUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LandingViewModel @ViewModelInject constructor(
    networkHelper: NetworkHelper,
    val navigator: Navigator,
    private val getPhotosUseCase: GetPhotosUseCase,
    private val sessionManager: SessionManager
) : IBaseViewModel(networkHelper) {

    private val _photo = MutableLiveData<String>()
    val photo: LiveData<String>
        get() = _photo

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun getPhotoPagination(): Flow<PagingData<PhotoResponse>> {
        return getPhotosUseCase.invoke(Unit)
    }

    fun getUser() {
        viewModelScope.launch {
            sessionManager.getUser()
                .collect {
                    _user.postValue(it)
                }
        }
    }

    fun openImage(downloadUrl: String) {
        _photo.postValue(downloadUrl)
    }

    fun logout() {
        viewModelScope.launch {
            sessionManager.logout()
            navigator.navigate(NavigateTo.Back)
        }
    }

    fun openEditScreen(source: Source?) {
        if (source != null)
            navigator.navigate(deepLinkToEditProfile(source.value))
    }
}