package com.efemoney.forecast.di

import android.content.Context
import com.efemoney.forecast.data.remote.Api
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(ApiModule::class))
interface ApiComponent {

    fun context(): Context

    fun api(): Api
    fun client(): OkHttpClient

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun module(module: ApiModule): Builder

        fun build(): ApiComponent
    }
}
