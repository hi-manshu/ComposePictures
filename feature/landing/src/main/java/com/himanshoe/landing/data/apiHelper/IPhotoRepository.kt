package com.himanshoe.landing.data.apiHelper

import com.himanshoe.landing.data.response.PhotoResponse
import javax.inject.Inject

class IPhotoRepository @Inject constructor(private val apiService: PhotoApiService
) : PhotoRepository {

    override suspend fun getPhotos(input: Pair<Int, Int>): List<PhotoResponse> {
        return apiService.getPhotos(input.first, input.second)
    }
}
