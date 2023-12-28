package com.kamalnayan.newsapp.data.api.interceptor

import com.kamalnayan.newsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/** @Author Kamal Nayan
Created on: 28/12/23
 *
 * Intercepts network call and adds api key query parameter to it
 **/
@Singleton
class NetworkCallInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url
        val newUrl = url.newBuilder().apply {
            addQueryParameter("apiKey", BuildConfig.API_KEY)
        }.build()

        val newRequest = request.newBuilder().url(newUrl).build()

        return chain.proceed(newRequest)
    }
}