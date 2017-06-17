package com.efemoney.forecast.data.model


sealed class QueryBy {

    data class Id(val id: String) : QueryBy() {

        override fun string() = id
    }

    data class GeoCoords(val lng: Double,
                         val lat: Double) : QueryBy() {

        override fun string() = "lon=$lng&lat=$lat"
    }

    data class CityName(val cityName: String,
                        val countryCode: String = "") : QueryBy() {

        override fun string() = if (countryCode.isEmpty()) cityName else "$cityName,$countryCode"
    }

    data class ZipCode(val zipCode: String,
                       val countryCode: String = "us") : QueryBy() {

        override fun string() = "$zipCode,$countryCode"
    }

    abstract fun string(): String
}
