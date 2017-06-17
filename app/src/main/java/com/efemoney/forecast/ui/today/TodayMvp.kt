package com.efemoney.forecast.ui.today

import com.efemoney.forecast.data.model.Location
import com.efemoney.forecast.ui.base.BasePresenter
import com.efemoney.forecast.ui.base.BaseView
import java.util.*

interface TodayMvp {

    interface View : BaseView<Presenter> {

        fun checkLocationPermission()

        fun requestLocationPermission()

        fun showLocationRationale()

        fun shouldShowLocationRationale(): Boolean

        fun showError(message: String)

        fun showGenericError()

        fun showProgressIndicator(showIndicator: Boolean)

        fun showUpdatedAtDate(date: Date)

        fun showWeatherIcon(iconUrl: String)

        fun showWeatherDescription(description: String?)

        fun showWeatherMinMaxTemp(min: String?, max: String?)

        fun showCrudeLocation(location: Location)

        fun showPrettyLocation(place: String?, countryCode: String?)
    }

    interface Presenter : BasePresenter {

        fun onLocationPermissionResult(granted: Boolean)

        fun onSearchLocationClick()
    }
}
