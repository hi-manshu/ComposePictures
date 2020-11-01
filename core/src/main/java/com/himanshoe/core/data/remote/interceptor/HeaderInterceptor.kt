package com.himanshoe.core.data.remote.interceptor

/**
 * Created by Himanshu Singh on 24-10-2020.
 **/
import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val requestBuilder = request.newBuilder()
        return chain.proceed(requestBuilder.build())
    }
}
