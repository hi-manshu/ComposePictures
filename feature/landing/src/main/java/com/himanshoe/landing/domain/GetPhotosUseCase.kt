package com.himanshoe.landing.domain

import com.himanshoe.core.base.IBaseUseCase
import com.himanshoe.core.util.IResult
import com.himanshoe.landing.data.response.PhotoResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: PhotoRepository) :
    IBaseUseCase<Pair<Int, Int>, List<PhotoResponse>> {

    override suspend fun invoke(input: Pair<Int, Int>): Flow<IResult<List<PhotoResponse>>> {
        return repository.getPhotos(input)
    }

}