package com.himanshoe.landing.data.apiHelper

import com.himanshoe.core.data.remote.util.performNetworkCall
import com.himanshoe.landing.data.response.PhotoResponse
import com.himanshoe.landing.domain.PhotoRepository
import com.himanshoe.core.util.IResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IPhotoRepository @Inject constructor(private val apiService: PhotoApiService) :
    PhotoRepository {

    override suspend fun getPhotos(input: Pair<Int, Int>): Flow<IResult<List<PhotoResponse>>> {
        return performNetworkCall {
            apiService.getAppConfig(input.first)
        }
    }

}