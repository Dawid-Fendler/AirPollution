package pl.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import pl.data.datasource.AirPollutionDataSource
import pl.data.repository.AirPollutionRepositoryImpl
import pl.model.station

class AirPollutionRepositoryTest {

    private val dataSource: AirPollutionDataSource = mock()
    private val repository = AirPollutionRepositoryImpl(dataSource)

    @Test
    fun `return stations when get stations is called`() {
        whenever(dataSource.getStations()).thenReturn(Single.just(listOf(station)))

        repository
            .getAirPollution()
            .test()
            .assertValue(listOf(station))
    }
}