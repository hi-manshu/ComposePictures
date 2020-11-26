package com.himanshoe.landing.data.apiHelper

import androidx.paging.PagingSource
import com.himanshoe.landing.data.response.PhotoResponse
import javax.inject.Inject


class PhotoSource @Inject constructor(private val photoRepository: PhotoRepository) :
    PagingSource<Int, PhotoResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponse> {
        return try {
            val page = params.key ?: 1
            val photoResponse = photoRepository.getPhotos(Pair(page, 100))
            LoadResult.Page(
                data = photoResponse,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}