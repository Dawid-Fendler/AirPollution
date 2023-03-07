package pl.data.datasource

import io.reactivex.rxjava3.core.Single
import pl.domain.model.Station

interface AirPollutionDataSource {
    fun getStations(): Single<List<Station>>
}
