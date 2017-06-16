package com.efemoney.forecast.data.remote.response

import com.google.gson.annotations.SerializedName

class WeatherData {

    @SerializedName("id") var id: Int = 0
    @SerializedName("cod") var cod: Int = 0
    @SerializedName("name") var name: String? = null
    @SerializedName("base") var base: String? = null
    @SerializedName("coord") var coordinates: Coordinates? = null
    @SerializedName("main") var main: Main? = null
    @SerializedName("wind") var wind: Wind? = null
    @SerializedName("rain") var rain: Rain? = null
    @SerializedName("clouds") var clouds: Clouds? = null
    @SerializedName("dt") var dataTime: Long = 0
    @SerializedName("sys") var sys: Sys? = null
    @SerializedName("weather") var weather: List<Weather>? = null

    class Coordinates {

        @SerializedName("lon") var lon: Double = 0.toDouble()
        @SerializedName("lat") var lat: Double = 0.toDouble()
    }

    class Main {

        @SerializedName("temp") var temp: Double = 0.toDouble()
        @SerializedName("humidity") var humidity: Int = 0
        @SerializedName("pressure") var pressure: Double = 0.toDouble()
        @SerializedName("temp_min") var tempMin: Double = 0.toDouble()
        @SerializedName("temp_max") var tempMax: Double = 0.toDouble()
        @SerializedName("sea_level") var seaLevel: Double = 0.toDouble()
        @SerializedName("grnd_level") var grndLevel: Double = 0.toDouble()
    }

    class Wind {
        @SerializedName("deg") var deg: Double = 0.toDouble()
        @SerializedName("speed") var speed: Double = 0.toDouble()
    }

    class Rain {

        @SerializedName("3h") var last3Hours: Double = 0.toDouble()
    }

    class Clouds {
        @SerializedName("all") var all: Int = 0
    }

    class Sys {
        @SerializedName("sunset") var sunset: Int = 0
        @SerializedName("sunrise") var sunrise: Int = 0
        @SerializedName("message") var message: Double = 0.toDouble()
        @SerializedName("country") var country: String? = null
    }

    class Weather {
        @SerializedName("id") var id: Int = 0
        @SerializedName("icon") var icon: String? = null
        @SerializedName("main") var main: String? = null
        @SerializedName("description") var description: String? = null
    }
}
