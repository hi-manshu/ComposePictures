package com.himanshoe.core.data.remote.util

import com.roomiapp.core.util.IResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.io.IOException

typealias NetworkAPIInvoke<T> = suspend () -> Response<T>


suspend fun <T : Any> performNetworkCall(
    messageInCaseOfError: String = "Network error",
    allowRetries: Boolean = true,
    numberOfRetries: Int = 2,
    networkApiCall: NetworkAPIInvoke<T>
): Flow<IResult<T>> {
    var delayDuration = 1000L
    val delayFactor = 2
    return flow {
        val response = networkApiCall()
        if (response.isSuccessful) {
            response.body()?.let { emit(IResult.OnSuccess(it)) }?: emit(IResult.OnFailed(IOException("API call successful but empty response body")))
            return@flow
        }
        emit(IResult.OnFailed(IOException("API call failed with error - ${response.errorBody()?.string() ?: messageInCaseOfError}")))
        return@flow
    }.catch { e ->
        emit(IResult.OnFailed(IOException("Exception during network API call: ${e.message}")))
        return@catch
    }.retryWhen { cause, attempt ->
        if (!allowRetries || attempt > numberOfRetries || cause !is IOException) return@retryWhen false
        delay(delayDuration)
        delayDuration *= delayFactor
        return@retryWhen true
    }.flowOn(Dispatchers.IO)
}
