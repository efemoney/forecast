package com.efemoney.forecast.data.service

import com.efemoney.forecast.data.model.Location
import io.reactivex.Observable

interface LocationProvider {

    fun retrieveLocation(): Observable<Location>
}