package com.efemoney.forecast.data.service

import android.content.Context
import com.efemoney.forecast.di.Activity
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides

@Module
class LocationModule {

    @Provides @Activity fun client(context: Context) = LocationServices.getFusedLocationProviderClient(context)

    @Provides @Activity fun provider(realProvider: RealLocationProvider): LocationProvider = realProvider
}