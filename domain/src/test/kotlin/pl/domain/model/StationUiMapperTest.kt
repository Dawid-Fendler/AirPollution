package pl.domain.model

import org.junit.Assert
import org.junit.Test
import pl.domain.mapper.toUiModel

class StationUiMapperTest {

    @Test
    fun `map station  to station ui model`() {
        val response = station
        val uiModel = response.toUiModel()

        Assert.assertEquals(uiModel, stationUiModel)
    }

    @Test
    fun `map station details to station details ui model`() {
        val response = stationDetails
        val uiModel = response.toUiModel()

        Assert.assertEquals(uiModel, stationDetailsUiModel)
    }
}