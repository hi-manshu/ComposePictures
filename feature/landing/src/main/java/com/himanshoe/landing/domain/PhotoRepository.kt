package com.himanshoe.landing.domain

import com.himanshoe.core.base.IBaseRepository
import com.himanshoe.landing.data.response.PhotoResponse
import com.himanshoe.core.util.IResult
import kotlinx.coroutines.flow.Flow


interface PhotoRepository : IBaseRepository {

    suspend fun getPhotos(input: Pair<Int, Int>): Flow<IResult<List<PhotoResponse>>>

}