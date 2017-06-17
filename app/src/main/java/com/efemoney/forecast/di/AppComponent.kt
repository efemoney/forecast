package com.efemoney.forecast.di

import android.content.Context
import com.efemoney.forecast.Application
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun app(): Application
    fun context(): Context

    @Component.Builder
    interface Builder {

        fun module(module: AppModule): Builder

        fun build(): AppComponent
    }
}