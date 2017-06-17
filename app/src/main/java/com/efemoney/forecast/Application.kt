package com.efemoney.forecast

import android.app.Application
import com.efemoney.forecast.data.remote.Api
import com.efemoney.forecast.data.source.DaggerRepoComponent
import com.efemoney.forecast.data.source.RepoComponent
import com.efemoney.forecast.di.*

class Application : Application() {

    lateinit var app: AppComponent
    lateinit var api: ApiComponent
    lateinit var repo: RepoComponent

    override fun onCreate() {
        super.onCreate()

        app = DaggerAppComponent.builder()
                .module(AppModule(this))
                .build()

        api = DaggerApiComponent.builder()
                .module(ApiModule(Api.BASE_URL))
                .context(app.context())
                .build()

        repo = DaggerRepoComponent.builder()
                .context(app.context())
                .api(api.api())
                .build()
    }
}