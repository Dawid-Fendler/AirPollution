package pl.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.data.repository.AirPollutionDetailsRepositoryImpl
import pl.data.repository.AirPollutionRepositoryImpl
import pl.domain.repository.AirPollutionDetailsRepository
import pl.domain.repository.AirPollutionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindAirPollutionRepository(impl: AirPollutionRepositoryImpl): AirPollutionRepository

    @Singleton
    @Binds
    fun bindAirPollutionDetailsRepository(impl: AirPollutionDetailsRepositoryImpl): AirPollutionDetailsRepository
}
