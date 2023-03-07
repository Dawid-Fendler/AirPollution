package pl.data.mapper

import pl.data.model.CityDto
import pl.data.model.CommuneDto
import pl.data.model.StationDto
import pl.domain.model.City
import pl.domain.model.Commune
import pl.domain.model.Station

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
