package pl.domain.mapper

import pl.domain.model.Station
import pl.domain.model.StationDetails
import pl.domain.model.StationDetailsUiModel
import pl.domain.model.StationUiModel

fun Station.toUiModel() = StationUiModel(
    id = id,
    stationName = "${city.name}\n $addressStreet",
    cityName = city.name,
    address = addressStreet.orEmpty(),
    communeName = city.commune.communeName,
    districtName = city.commune.districtName,
    provinceName = city.commune.provinceName
)

fun StationDetails.toUiModel() = StationDetailsUiModel(
    airPollutionDescription = "Data Pomiaru:$stDate\n\n" +
            "- stIndexLevel:${stIndexLevel.indexLevelName}\n\n" +
            "- so2IndexLevel:${so2IndexLevel.indexLevelName}\n\n" +
            "- no2IndexLevel:${no2IndexLevel.indexLevelName}\n\n" +
            "- pm10IndexLevel:${pm10IndexLevel.indexLevelName}\n\n" +
            "- pm25IndexLevel:${pm25IndexLevel.indexLevelName}\n\n" +
            "- o3IndexLevel:${o3IndexLevel.indexLevelName}"
)
