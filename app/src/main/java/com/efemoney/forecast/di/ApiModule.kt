package com.efemoney.forecast.di

import com.efemoney.forecast.data.remote.AuthInterceptor
import com.efemoney.forecast.data.remote.ConnectivityChecker
import com.efemoney.forecast.data.remote.OwmApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule(private val baseUrl: String) {

    @Provides @Singleton fun gson(): Gson {

        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
    }

    @Provides @Singleton fun okHttpClient(checker: ConnectivityChecker): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val authInterceptor = AuthInterceptor(OwmApi.API_KEY)

        return OkHttpClient.Builder()
                .addInterceptor(checker)
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
    }

    @Provides @Singleton fun retrofit(client: OkHttpClient, gson: Gson): Retrofit {

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides @Singleton fun api(retrofit: Retrofit): OwmApi {

        return retrofit.create<OwmApi>() // Arguably better syntax
    }

    inline fun <reified T> Retrofit.create(): T {
        return create(T::class.java)
    }
}