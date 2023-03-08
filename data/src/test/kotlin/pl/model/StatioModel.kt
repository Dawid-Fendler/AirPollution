package pl.model

import pl.data.model.CityDto
import pl.data.model.CommuneDto
import pl.data.model.IndexLevelDto
import pl.data.model.StationDetailsDto
import pl.data.model.StationDto
import pl.domain.model.City
import pl.domain.model.Commune
import pl.domain.model.IndexLevel
import pl.domain.model.Station
import pl.domain.model.StationDetails

val stationDto = StationDto(
    id = 1,
    stationName = "Name",
    gegrLat = "12.12",
    gegrLon = "12.12",
    city = CityDto(
        id = 1,
        name = "Name",
        CommuneDto(
            communeName = "Commune",
            districtName = "District",
            provinceName = "Province"
        )
    ),
    addressStreet = "Address"
)

val station = Station(
    id = 1,
    name = "Name",
    latitude = "12.12",
    longitude = "12.12",
    city = City(
        id = 1,
        name = "Name",
        Commune(
            communeName = "Commune",
            districtName = "District",
            provinceName = "Province"
        )
    ),
    addressStreet = "Address"
)

val stationDetailsDto = StationDetailsDto(
    id = 1,
    stCalcDate = "10.12.2015",
    stIndexLevel = IndexLevelDto(id = 1, indexLevelName = "Dobry"),
    so2IndexLevel = IndexLevelDto(id = 1, indexLevelName = "Dobry"),
    no2IndexLevel = IndexLevelDto(id = 1, indexLevelName = "Dobry"),
    pm10IndexLevel = IndexLevelDto(id = 1, indexLevelName = "Dobry"),
    pm25IndexLevel = IndexLevelDto(id = 1, indexLevelName = "Dobry"),
    o3IndexLevel = IndexLevelDto(id = 1, indexLevelName = "Dobry")
)

val stationDetails = StationDetails(
    id = 1,
    stDate = "10.12.2015",
    stIndexLevel = IndexLevel(id = 1, indexLevelName = "Dobry"),
    so2IndexLevel = IndexLevel(id = 1, indexLevelName = "Dobry"),
    no2IndexLevel = IndexLevel(id = 1, indexLevelName = "Dobry"),
    pm10IndexLevel = IndexLevel(id = 1, indexLevelName = "Dobry"),
    pm25IndexLevel = IndexLevel(id = 1, indexLevelName = "Dobry"),
    o3IndexLevel = IndexLevel(id = 1, indexLevelName = "Dobry")
)
