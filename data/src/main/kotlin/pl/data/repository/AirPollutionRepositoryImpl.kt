package pl.data.repository

import io.reactivex.rxjava3.core.Single
import pl.data.datasource.AirPollutionDataSource
import pl.domain.model.Station
import pl.domain.repository.AirPollutionRepository
import javax.inject.Inject

class AirPollutionRepositoryImpl @Inject constructor(
    private val dataSource: AirPollutionDataSource
) : AirPollutionRepository {

    override fun getAirPollution(): Single<List<Station>> {
        return dataSource.getStations()
    }
}
