package pl.stations

import pl.domain.model.IndexLevel
import pl.domain.model.StationDetails
import pl.domain.model.StationDetailsUiModel

val stationDetailsUiModel = StationDetailsUiModel(
    airPollutionDescription = "Data Pomiaru:10.12.2015\n\n" +
            "- stIndexLevel:Dobry\n\n" +
            "- so2IndexLevel:Dobry\n\n" +
            "- no2IndexLevel:Dobry\n\n" +
            "- pm10IndexLevel:Dobry\n\n" +
            "- pm25IndexLevel:Dobry\n\n" +
            "- o3IndexLevel:Dobry"
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