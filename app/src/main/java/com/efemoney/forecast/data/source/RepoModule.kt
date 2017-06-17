package com.efemoney.forecast.data.source

import com.efemoney.forecast.data.source.remote.RemoteDataSource
import com.efemoney.forecast.di.Remote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides @Singleton @Remote fun remoteSource(source: RemoteDataSource): DataSource = source
}