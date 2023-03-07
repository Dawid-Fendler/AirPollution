package pl.data.datasource

import io.reactivex.rxjava3.core.Single
import pl.data.mapper.toDomain
import pl.data.service.AirPollutionApi
import pl.domain.model.Station
import javax.inject.Inject

internal class AirPollutionRemoteDataSource @Inject constructor(
    private val api: AirPollutionApi
) : AirPollutionDataSource {

    override fun getStations(): Single<List<Station>> {
        return api.getStations().map { it.map { stationDto -> stationDto.toDomain() } }
    }
}
