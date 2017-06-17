package com.efemoney.forecast.ui.today

import com.efemoney.forecast.data.service.LocationModule
import com.efemoney.forecast.data.source.RepoComponent
import com.efemoney.forecast.di.Activity
import dagger.Component

@Activity
@Component(
        modules = arrayOf(TodayModule::class, LocationModule::class),
        dependencies = arrayOf(RepoComponent::class))
interface TodayComponent {

    fun inject(ac: TodayActivity)
}