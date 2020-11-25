package com.himanshoe.landing.data.apiHelper

import android.util.Log
import androidx.paging.PagingSource
import com.himanshoe.landing.data.response.PhotoResponse
import com.himanshoe.landing.domain.PhotoRepository
import javax.inject.Inject

class PhotoSource @Inject constructor(private val photoRepository: PhotoRepository) :
    PagingSource<Int, PhotoResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponse> {
        return try {
            val nextPage = params.key ?: 1
            val photoResponse = photoRepository.getPhotos(Pair(nextPage, 100))
            LoadResult.Page(
                data = photoResponse,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}