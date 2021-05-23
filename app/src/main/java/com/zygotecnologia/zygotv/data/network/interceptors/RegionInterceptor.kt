package com.zygotecnologia.zygotv.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class RegionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val region = "BR"
        val url = original.url.newBuilder().addQueryParameter("region", region).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}
