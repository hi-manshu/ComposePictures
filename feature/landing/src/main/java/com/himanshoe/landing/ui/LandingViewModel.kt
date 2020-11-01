package com.himanshoe.landing.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.himanshoe.core.base.IBaseViewModel
import com.himanshoe.core.navigator.Navigator
import com.himanshoe.core.util.IResult
import com.himanshoe.core.util.NetworkHelper
import com.himanshoe.landing.data.response.PhotoResponse
import com.himanshoe.landing.domain.GetPhotosUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LandingViewModel @ViewModelInject constructor(
    networkHelper: NetworkHelper,
    val navigator: Navigator,
    private val getPhotosUseCase: GetPhotosUseCase
) : IBaseViewModel(networkHelper) {

    private val _pageNumber = MutableLiveData<Int>()
    private val pageNumber: LiveData<Int>
        get() = _pageNumber

    private val _photos = MutableLiveData<List<PhotoResponse>>()
    val photos: LiveData<List<PhotoResponse>>
        get() = _photos

    fun loadMorePhotos() {
        pageNumber.value?.plus(1)?.let { init(it) }
    }

    fun init(pageNumber: Int) {
        viewModelScope.launch {
            _pageNumber.postValue(pageNumber)
            getPhotosUseCase.invoke(Pair(pageNumber, 100))
                .catch {
                    _photos.postValue(emptyList())
                }.collect { result ->
                    when (result) {
                        is IResult.OnSuccess -> _photos.postValue(result.response)
                        is IResult.OnFailed -> _photos.postValue(emptyList())
                    }
                }
        }
    }
}