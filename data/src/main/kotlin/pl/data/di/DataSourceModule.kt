package pl.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.data.datasource.AirPollutionDataSource
import pl.data.datasource.AirPollutionDetailsDataSource
import pl.data.datasource.AirPollutionDetailsRemoteDataSource
import pl.data.datasource.AirPollutionRemoteDataSource
import pl.data.service.AirPollutionApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideAirPollutionDataSource(api: AirPollutionApi): AirPollutionDataSource =
        AirPollutionRemoteDataSource(api)

    @Singleton
    @Provides
    fun provideAirPollutionDetailsDataSource(api: AirPollutionApi): AirPollutionDetailsDataSource =
        AirPollutionDetailsRemoteDataSource(api)
}
