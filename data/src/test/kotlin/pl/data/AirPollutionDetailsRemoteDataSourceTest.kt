package pl.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import pl.data.datasource.AirPollutionDetailsRemoteDataSource
import pl.data.service.AirPollutionApi
import pl.model.stationDetails
import pl.model.stationDetailsDto

class AirPollutionDetailsRemoteDataSourceTest {

    private val api: AirPollutionApi = mock()
    private val datasource = AirPollutionDetailsRemoteDataSource(api)

    @Test
    fun `get station details and map to domain model`() {
        whenever(api.getAirPollutionDetails(1)).thenReturn(Single.just(stationDetailsDto))

        datasource.getAirPollutionDetails(1).test().assertResult(stationDetails)
    }
}