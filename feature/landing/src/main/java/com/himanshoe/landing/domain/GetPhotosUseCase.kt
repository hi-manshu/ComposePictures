package com.himanshoe.landing.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.himanshoe.core.base.IBaseUseCase
import com.himanshoe.landing.data.apiHelper.PhotoSource
import com.himanshoe.landing.data.response.PhotoResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val photoSource: PhotoSource) :
    IBaseUseCase<Unit, PagingData<PhotoResponse>> {

    override fun invoke(input: Unit): Flow<PagingData<PhotoResponse>> {
        return Pager(PagingConfig(pageSize = 20)) {
            photoSource
        }.flow
    }

}