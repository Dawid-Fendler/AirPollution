package pl.domain.model

data class StationDetails(
    val id: Int,
    val stDate: String,
    val stIndexLevel: IndexLevel,
    val so2IndexLevel: IndexLevel,
    val no2IndexLevel: IndexLevel,
    val pm10IndexLevel: IndexLevel,
    val pm25IndexLevel: IndexLevel,
    val o3IndexLevel: IndexLevel,
)
