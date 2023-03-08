package pl.domain.mapper

import pl.domain.model.Station
import pl.domain.model.StationUiModel

fun Station.toUiModel() = StationUiModel(
    id = id,
    stationName = "${city.name}\n $addressStreet"
)
