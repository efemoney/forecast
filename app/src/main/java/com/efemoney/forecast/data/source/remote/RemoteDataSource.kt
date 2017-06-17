package com.efemoney.forecast.data.source.remote

import com.efemoney.forecast.data.model.QueryBy
import com.efemoney.forecast.data.remote.Api
import com.efemoney.forecast.data.source.DataSource
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val api: Api): DataSource {

    override fun getWeather(query: QueryBy) = when (query) {

        is QueryBy.Id -> api.weatherByCityId(query.string())
        is QueryBy.ZipCode -> api.weatherByZipCodes(query.string())
        is QueryBy.CityName -> api.weatherByCityName(query.string())
        is QueryBy.GeoCoords -> api.weatherByGeoCoords(query.lng, query.lat)
    }
}