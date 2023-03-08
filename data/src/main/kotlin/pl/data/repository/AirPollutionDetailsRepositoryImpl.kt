package pl.data.repository

import io.reactivex.rxjava3.core.Single
import pl.data.datasource.AirPollutionDetailsDataSource
import pl.domain.model.StationDetails
import pl.domain.repository.AirPollutionDetailsRepository
import javax.inject.Inject

class AirPollutionDetailsRepositoryImpl @Inject constructor(
    private val dataSource: AirPollutionDetailsDataSource
) : AirPollutionDetailsRepository {

    override fun getAirPollutionDetails(id: Int): Single<StationDetails> {
        return dataSource.getAirPollutionDetails(id)
    }
}
