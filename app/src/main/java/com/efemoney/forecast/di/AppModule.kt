package com.efemoney.forecast.di

import android.content.Context
import com.efemoney.forecast.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides @Singleton fun app() = app

    @Provides @Singleton fun context(): Context = app
}
