package com.efemoney.forecast.data.remote.request

class ZipCodeQuery(val zipCode: String, val countryCode: String = "us") {

    override fun toString() = "$zipCode,$countryCode"
}