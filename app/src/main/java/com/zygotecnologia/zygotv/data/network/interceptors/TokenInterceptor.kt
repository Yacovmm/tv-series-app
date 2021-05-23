package com.zygotecnologia.zygotv.data.network.interceptors

import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.data.network.TmdbApi.Companion.TMDB_API_QUERY
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val token = BuildConfig.API_KEY
        val url = original.url.newBuilder().addQueryParameter(TMDB_API_QUERY, token).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}

