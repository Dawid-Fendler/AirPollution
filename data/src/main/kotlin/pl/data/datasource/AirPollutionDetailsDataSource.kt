package pl.data.datasource

import io.reactivex.rxjava3.core.Single
import pl.domain.model.StationDetails

interface AirPollutionDetailsDataSource {
    fun getAirPollutionDetails(id: Int): Single<StationDetails>
}
