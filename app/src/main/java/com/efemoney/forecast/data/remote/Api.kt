package com.efemoney.forecast.data.remote

import com.efemoney.forecast.BuildConfig
import com.efemoney.forecast.data.remote.request.CityNameQuery
import com.efemoney.forecast.data.remote.request.ZipCodeQuery
import com.efemoney.forecast.data.remote.response.WeatherData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OwmApi {

    @GET("weather")
    fun weatherByCityId(@Query("id") id: String): Single<WeatherData>

    @GET("weather")
    fun weatherByCityName(@Query("q") query : CityNameQuery): Single<WeatherData>

    @GET("weather")
    fun weatherByZipCodes(@Query("zip") query : ZipCodeQuery): Single<WeatherData>

    @GET("weather")
    fun weatherByGeoCoords(@Query("lon") lng: String, @Query("lat") lat: String): Single<WeatherData>

    companion object {

        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        const val BASE_IMG_URL = "http://openweathermap.org/img/w/"

        const val API_KEY = BuildConfig.OWM_API_KEY
    }
}
