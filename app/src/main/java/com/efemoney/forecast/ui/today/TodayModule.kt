package com.efemoney.forecast.ui.today

import dagger.Module
import dagger.Provides

@Module
class TodayModule(private val view: TodayMvp.View) {

    @Provides fun view() = view
}