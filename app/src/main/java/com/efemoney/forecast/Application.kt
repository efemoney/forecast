package com.efemoney.forecast

import android.app.Application
import com.efemoney.forecast.data.remote.OwmApi
import com.efemoney.forecast.di.ApiModule
import com.efemoney.forecast.di.AppComponent
import com.efemoney.forecast.di.AppModule
import com.efemoney.forecast.di.DaggerAppComponent

class Application : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

         component = DaggerAppComponent.builder()
                 .appModule(AppModule(this))
                 .apiModule(ApiModule(OwmApi.BASE_URL))
                 .build()
    }
}