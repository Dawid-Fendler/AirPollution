package pl.stations

import pl.domain.model.City
import pl.domain.model.Commune
import pl.domain.model.Station
import pl.domain.model.StationUiModel

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

val stationUiModel = StationUiModel(
    id = 1,
    stationName = "Name\n Address",
    cityName = "Name",
    address = "Address",
    communeName = "Commune",
    districtName = "District",
    provinceName = "Province"
)