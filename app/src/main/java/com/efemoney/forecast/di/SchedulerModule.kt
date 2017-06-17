package com.efemoney.forecast.di

import com.efemoney.forecast.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class SchedulerModule {

    @Provides fun scheduler(): SchedulerProvider {

        return object : SchedulerProvider {

            override fun io() = Schedulers.io()
            override fun single() = Schedulers.single()
            override fun trampoline() = Schedulers.trampoline()
            override fun mainThread() = AndroidSchedulers.mainThread()
        }
    }
}