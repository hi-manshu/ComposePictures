package com.himanshoe.landing.data.apiHelper

import com.himanshoe.core.base.IBaseRepository
import com.himanshoe.landing.data.response.PhotoResponse


interface PhotoRepository : IBaseRepository {

    suspend fun getPhotos(input: Pair<Int, Int>): List<PhotoResponse>

}