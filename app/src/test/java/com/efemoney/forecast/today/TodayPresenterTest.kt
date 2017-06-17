package com.efemoney.forecast.today

import com.efemoney.forecast.SchedulerProvider
import com.efemoney.forecast.data.model.Location
import com.efemoney.forecast.data.model.QueryBy
import com.efemoney.forecast.data.model.WeatherData
import com.efemoney.forecast.data.service.LocationProvider
import com.efemoney.forecast.data.source.Repository
import com.efemoney.forecast.ui.today.TodayMvp
import com.efemoney.forecast.ui.today.TodayPresenter
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class TodayPresenterTest {

    @Mock lateinit var repository: Repository
    @Mock lateinit var provider: LocationProvider
    @Mock lateinit var view: TodayMvp.View

    private lateinit var presenter: TodayMvp.Presenter

    val lng = 1.0
    val lat = 2.0

    val query = QueryBy.GeoCoords(lng, lat)
    val location = Location(lng, lat)
    val weather = WeatherData()

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        `when`(provider.retrieveLocation()).thenReturn(Observable.just(location))

        `when`(repository.getWeather(query)).thenReturn(Single.just(weather))

        presenter = TodayPresenter(repository, provider, view, object : SchedulerProvider {

            override fun io() = Schedulers.trampoline()

            override fun single() = Schedulers.trampoline()

            override fun mainThread() = Schedulers.trampoline()

            override fun trampoline() = Schedulers.trampoline()
        })
    }

    @Test
    fun test_retrieve_location_on_location_permission_granted() {

        presenter.onLocationPermissionResult(true)

        verify(view, atLeastOnce()).showProgressIndicator(true);

        verify(view, atLeastOnce()).showProgressIndicator(false);

        verify(view, atLeastOnce()).showCrudeLocation(location)
    }

    @Test
    fun test_ask_for_permission_on_location_permission_not_granted() {

        `when`(view.shouldShowLocationRationale()).thenReturn(false)

        presenter.onLocationPermissionResult(false)

        verify(view).requestLocationPermission()
    }

    @Test
    fun test_show_permission_rationale_on_location_permission_not_granted() {

        `when`(view.shouldShowLocationRationale()).thenReturn(true)

        presenter.onLocationPermissionResult(false)

        verify(view).showLocationRationale()
    }
}