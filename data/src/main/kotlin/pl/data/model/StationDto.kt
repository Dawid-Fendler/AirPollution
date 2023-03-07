package pl.data.model

data class StationDto(
    val id: Int,
    val stationName: String,
    val gegrLat: String,
    val gegrLon: String,
    val city: CityDto,
    val addressStreet: String?
)
