package com.roomiapp.core.util

sealed class IResult<out DTO : Any> {
    data class OnSuccess<out DTO : Any>(val response: DTO) : IResult<DTO>()
    data class OnFailed(val throwable: Throwable) : IResult<Nothing>()
}