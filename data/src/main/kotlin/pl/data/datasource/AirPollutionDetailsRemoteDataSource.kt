package pl.data.datasource

import io.reactivex.rxjava3.core.Single
import pl.data.mapper.toDomain
import pl.data.service.AirPollutionApi
import pl.domain.model.StationDetails
import javax.inject.Inject

internal class AirPollutionDetailsRemoteDataSource @Inject constructor(
    private val api: AirPollutionApi
) : AirPollutionDetailsDataSource {

    override fun getAirPollutionDetails(id: Int): Single<StationDetails> {
        return api.getAirPollutionDetails(id).map { it.toDomain() }
    }
}
