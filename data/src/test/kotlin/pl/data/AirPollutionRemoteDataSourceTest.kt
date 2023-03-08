package pl.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import pl.data.datasource.AirPollutionRemoteDataSource
import pl.data.service.AirPollutionApi
import pl.model.station
import pl.model.stationDto

class AirPollutionRemoteDataSourceTest {

    private val api: AirPollutionApi = mock()
    private val datasource = AirPollutionRemoteDataSource(api)

    @Test
    fun `get stations and map to domain model`() {
        whenever(api.getStations()).thenReturn(Single.just(listOf(stationDto)))

        datasource.getStations().test().assertResult(listOf(station))
    }
}
