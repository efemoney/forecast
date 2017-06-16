package com.efemoney.forecast.data.remote.request

class CityNameQuery(val cityName: String, val countryCode: String = "") {

    override fun toString() = if (countryCode.isEmpty()) cityName else "$cityName,$countryCode"
}