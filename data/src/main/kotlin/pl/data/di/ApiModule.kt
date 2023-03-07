package pl.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.data.service.AirPollutionApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideAirPollutionApi(retrofit: Retrofit): AirPollutionApi =
        retrofit.create(AirPollutionApi::class.java)
}
