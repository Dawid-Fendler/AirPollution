package pl.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import pl.data.datasource.AirPollutionDetailsDataSource
import pl.data.repository.AirPollutionDetailsRepositoryImpl
import pl.model.stationDetails

class AirPollutionDetailsRepositoryTest {

    private val dataSource: AirPollutionDetailsDataSource = mock()
    private val repository = AirPollutionDetailsRepositoryImpl(dataSource)

    @Test
    fun `return station details when get air pollution details is called`() {
        whenever(dataSource.getAirPollutionDetails(1)).thenReturn(Single.just(stationDetails))

        repository
            .getAirPollutionDetails(1)
            .test()
            .assertValue(stationDetails)
    }
}