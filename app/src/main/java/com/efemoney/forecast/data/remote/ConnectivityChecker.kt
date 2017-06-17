package com.efemoney.forecast.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.efemoney.forecast.util.service
import okhttp3.*
import javax.inject.Inject

class ConnectivityChecker @Inject constructor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val cm = context.service<ConnectivityManager>()

        val ni: NetworkInfo? = cm.activeNetworkInfo

        if (ni == null || !ni.isConnectedOrConnecting) { // Network Unavailable

            return Response.Builder()
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(404)
                    .message("Internet connection error")
                    .body(ResponseBody.create(MEDIA_TYPE, BODY))
                    .sentRequestAtMillis(-1L)
                    .receivedResponseAtMillis(System.currentTimeMillis())
                    .build()
        }

        return chain.proceed(chain.request())
    }

    companion object {

        private val MEDIA_TYPE: MediaType? = MediaType.parse("application/json; charset=UTF-8")
        private const val BODY = "{\"cod\": 404,\"message\": \"Please check your internet connection and try again.\"}"
    }
}

