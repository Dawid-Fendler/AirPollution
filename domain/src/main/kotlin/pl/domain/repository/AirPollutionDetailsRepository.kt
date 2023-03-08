package pl.domain.repository

import io.reactivex.rxjava3.core.Single
import pl.domain.model.StationDetails

interface AirPollutionDetailsRepository {
    fun getAirPollutionDetails(id: Int): Single<StationDetails>
}
