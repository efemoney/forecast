package com.efemoney.forecast.data.source

import android.content.Context
import com.efemoney.forecast.data.remote.Api
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(RepoModule::class))
interface RepoComponent {

    fun repo(): Repository

    fun context(): Context

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun api(api: Api): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): RepoComponent
    }
}