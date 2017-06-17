package com.efemoney.forecast.data.remote;

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response? {

        val url = chain.request().url()

        val newRequest = chain.request().newBuilder()
                .url(url.newBuilder().addQueryParameter("appid", token).build())
                .build()

        return chain.proceed(newRequest)
    }
}
