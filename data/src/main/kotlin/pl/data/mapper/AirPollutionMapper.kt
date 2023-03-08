package pl.data.mapper

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

fun StationDto.toDomain() = Station(
    id = id,
    name = stationName,
    latitude = gegrLat,
    longitude = gegrLon,
    city = city.toDomain(),
    addressStreet = addressStreet
)

fun CityDto.toDomain() = City(
    id = id,
    name = name,
    commune = commune.toDomain()
)

fun CommuneDto.toDomain() = Commune(
    communeName = communeName,
    districtName = districtName,
    provinceName = provinceName
)

fun StationDetailsDto.toDomain() = StationDetails(
    id = id,
    stDate = stCalcDate,
    stIndexLevel = stIndexLevel?.toDomain() ?: IndexLevel(0, "Brak Informacji"),
    so2IndexLevel = so2IndexLevel?.toDomain() ?: IndexLevel(0, "Brak Informacji"),
    no2IndexLevel = no2IndexLevel?.toDomain() ?: IndexLevel(0, "Brak Informacji"),
    pm10IndexLevel = pm10IndexLevel?.toDomain() ?: IndexLevel(0, "Brak Informacji"),
    pm25IndexLevel = pm25IndexLevel?.toDomain() ?: IndexLevel(0, "Brak Informacji"),
    o3IndexLevel = o3IndexLevel?.toDomain() ?: IndexLevel(0, "Brak Informacji")
)

fun IndexLevelDto.toDomain() = IndexLevel(
    id = id,
    indexLevelName = indexLevelName
)
