package com.efemoney.forecast.di

import android.content.Context
import com.efemoney.forecast.Application
import com.efemoney.forecast.data.remote.OwmApi
import com.efemoney.forecast.ui.today.TodayActivity
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(AppModule::class, ApiModule::class))
interface AppComponent {

    fun api(): OwmApi
    fun app(): Application
    fun context(): Context
    fun client(): OkHttpClient

    fun inject(ac: TodayActivity)
}
