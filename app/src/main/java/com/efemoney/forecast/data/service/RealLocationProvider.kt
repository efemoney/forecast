package com.efemoney.forecast.data.service

import android.annotation.SuppressLint
import com.efemoney.forecast.data.model.Location
import com.efemoney.forecast.di.Activity
import com.google.android.gms.location.*
import io.reactivex.Observable
import javax.inject.Inject

@Activity
class RealLocationProvider @Inject constructor(
        val client: FusedLocationProviderClient) : LocationProvider {

    @SuppressLint("MissingPermission")
    override fun retrieveLocation(): Observable<Location> = Observable.create { e ->

        val req = LocationRequest().apply {
            priority = LocationRequest.PRIORITY_LOW_POWER
            numUpdates = 1
            interval = 120000 // 2min
            fastestInterval = 30000 // 30sec
        }

        val cb = object : LocationCallback() {

            override fun onLocationResult(result: LocationResult) {

                if (e.isDisposed) return

                val loc = result.lastLocation

                if (loc == null)
                    e.onError(Throwable("Cannot retrieve location. try again later"))
                else
                    e.onNext(Location.from(loc))
            }

            override fun onLocationAvailability(avail: LocationAvailability) {

                if (!avail.isLocationAvailable && !e.isDisposed)
                    e.onError(Throwable("Cannot retrieve location. try again later"))
            }
        }

        e.setCancellable { client.removeLocationUpdates(cb) }

        client.lastLocation.addOnSuccessListener {

            if (it == null) {
                client.requestLocationUpdates(req, cb, null)
            } else {
                if (!e.isDisposed) e.onNext(Location.from(it))
            }
        }
    }
}