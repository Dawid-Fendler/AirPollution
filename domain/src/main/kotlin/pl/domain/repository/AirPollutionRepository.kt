package pl.domain.repository

import io.reactivex.rxjava3.core.Single
import pl.domain.model.Station

interface AirPollutionRepository {
    fun getAirPollution(): Single<List<Station>>
}
