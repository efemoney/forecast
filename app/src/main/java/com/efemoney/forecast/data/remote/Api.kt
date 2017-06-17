package com.efemoney.forecast.data.remote

import com.efemoney.forecast.BuildConfig
import com.efemoney.forecast.data.model.WeatherData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("weather?units=metric")
    fun weatherByCityId(@Query("id") id: String): Single<WeatherData>

    @GET("weather?units=metric")
    fun weatherByCityName(@Query("q") query : String): Single<WeatherData>

    @GET("weather?units=metric")
    fun weatherByZipCodes(@Query("zip") query : String): Single<WeatherData>

    @GET("weather?units=metric")
    fun weatherByGeoCoords(@Query("lon") lng: Double, @Query("lat") lat: Double): Single<WeatherData>

    companion object {

        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        const val BASE_IMG_URL = "http://openweathermap.org/img/w/"

        const val API_KEY = BuildConfig.OWM_API_KEY
    }
}
