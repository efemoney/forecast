package com.efemoney.forecast.data.remote.request

class GeoCoords(val lng: Double, val lat: Double) {

    override fun toString(): String {

        return "lon=$lng&lat=$lat"
    }
}