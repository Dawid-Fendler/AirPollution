package pl.data.model

data class StationDetailsDto(
    val id: Int,
    val stCalcDate: String,
    val stIndexLevel: IndexLevelDto?,
    val so2IndexLevel: IndexLevelDto?,
    val no2IndexLevel: IndexLevelDto?,
    val pm10IndexLevel: IndexLevelDto?,
    val pm25IndexLevel: IndexLevelDto?,
    val o3IndexLevel: IndexLevelDto?,
)
