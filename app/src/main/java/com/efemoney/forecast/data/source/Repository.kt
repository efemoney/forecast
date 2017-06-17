package com.efemoney.forecast.data.source

import com.efemoney.forecast.data.model.QueryBy
import com.efemoney.forecast.data.model.WeatherData
import com.efemoney.forecast.di.Remote
import io.reactivex.Single
import javax.inject.Inject

class Repository @Inject constructor(
        @Remote private val remoteSource: DataSource) : DataSource {

    override fun getWeather(query: QueryBy): Single<WeatherData> {

        return remoteSource.getWeather(query)
    }
}