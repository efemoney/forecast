package com.efemoney.forecast.data.model

import android.location.Location

data class Location(val lng: Double, val lat: Double){

    companion object {

        @JvmStatic fun from(loc: Location) = Location(loc.longitude, loc.latitude)
    }
}