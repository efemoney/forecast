package com.efemoney.forecast.data.remote;

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response? {

        val authedRequest = chain.request()
                ?.newBuilder()
                ?.addHeader("Authorization", "Bearer: $token")
                ?.build();

        return chain.proceed(authedRequest);
    }
}
