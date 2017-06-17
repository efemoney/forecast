package com.efemoney.forecast.ui.today

import com.efemoney.forecast.data.model.Location
import com.efemoney.forecast.data.model.QueryBy
import com.efemoney.forecast.data.model.WeatherData
import com.efemoney.forecast.data.remote.Api
import com.efemoney.forecast.data.service.LocationProvider
import com.efemoney.forecast.data.source.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TodayPresenter @Inject constructor(private val repository: Repository,
                                         private val locationProvider: LocationProvider,
                                         private val view: TodayMvp.View) : TodayMvp.Presenter {

    var retrieveLocationDisposable: Disposable? = null
    var retrieveWeatherDisposable: Disposable? = null

    override fun subscribe() {

        view.checkLocationPermission()
    }

    override fun unsubscribe() {
        retrieveLocationDisposable?.dispose()
        retrieveWeatherDisposable?.dispose()
    }

    override fun onLocationPermissionResult(granted: Boolean) {

        if (granted) {

            view.showProgressIndicator(true)

            retrieveLocationDisposable = locationProvider.retrieveLocation()

                    .subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())

                    .doFinally { view.showProgressIndicator(false) }

                    .subscribe(
                            { onLocationRetrieved(it) }, // onNext
                            { onRetrievingLocationError(it) } // onError
                    )

            return
        }

        if (view.shouldShowLocationRationale())
            view.showLocationRationale()
        else
            view.requestLocationPermission()
    }

    private fun onLocationRetrieved(location: Location?) {

        if (location == null) return

        retrieveLocationDisposable?.dispose() // We dont need location anymore right now
        retrieveWeatherDisposable?.dispose() // Stop any previous calls

        view.showCrudeLocation(location)
        view.showProgressIndicator(true)

        val query = QueryBy.GeoCoords(location.lng, location.lat)
        retrieveWeatherDisposable = repository.getWeather(query)

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .doFinally { view.showProgressIndicator(false) }

                .subscribe(

                        { onWeatherRetrieved(it) }, // onNext
                        { onRetrievingWeatherError(it) } // onError
                )

    }

    private fun onWeatherRetrieved(data: WeatherData) {

        view.showPrettyLocation(data.name, data.sys?.country)

        view.showUpdatedAtDate(Date(TimeUnit.SECONDS.toMillis(data.timeSeconds)))

        if (data.weather != null && data.weather!!.isNotEmpty()) {

            val weather = data.weather!![0]

            view.showWeatherIcon("${Api.BASE_IMG_URL}${weather.icon}.png")
            view.showWeatherDescription(weather.description)
        }

        view.showWeatherMinMaxTemp(
                min = data.main?.tempMin?.toString(),
                max = data.main?.tempMax?.toString()
        )
    }

    private fun onRetrievingLocationError(error: Throwable?) {

        if (error == null)
            view.showGenericError()
        else
            view.showError(error.message?: "Something went wrong")
    }

    private fun onRetrievingWeatherError(error: Throwable?) {

        if (error is HttpException) view.showError(error.message())

        if (error == null)
            view.showGenericError()
        else
            view.showError(error.message?: "Something went wrong")
    }


    override fun onSearchLocationClick() {
        // TODO Unimplemented
    }
}