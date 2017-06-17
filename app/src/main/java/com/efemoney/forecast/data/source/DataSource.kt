package com.efemoney.forecast.data.source

import com.efemoney.forecast.data.model.QueryBy
import com.efemoney.forecast.data.model.WeatherData
import io.reactivex.Single

interface DataSource {

    fun getWeather(query: QueryBy): Single<WeatherData>
}