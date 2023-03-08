package pl.data

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.data.mapper.toDomain
import pl.model.station
import pl.model.stationDetails
import pl.model.stationDetailsDto
import pl.model.stationDto

class AirPollutionMapperTest {

    @Test
    fun `map station dto to station domain model`() {
        val response = stationDto
        val domain = response.toDomain()

        assertEquals(domain, station)
    }

    @Test
    fun `map station details dto to station details domain model`() {
        val response = stationDetailsDto
        val domain = response.toDomain()

        assertEquals(domain, stationDetails)
    }
}