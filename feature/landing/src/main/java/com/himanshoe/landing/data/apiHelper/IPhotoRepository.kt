package com.himanshoe.landing.data.apiHelper

import com.himanshoe.landing.data.response.PhotoResponse
import com.himanshoe.landing.domain.PhotoRepository
import javax.inject.Inject

class IPhotoRepository @Inject constructor(
    private val apiService: PhotoApiService
) : PhotoRepository {

    override suspend fun getPhotos(input: Pair<Int, Int>): List<PhotoResponse> {
        return apiService.getAppConfig(input.first, input.second)
    }

}