package pl.data.model

data class CityDto(
    val id: Int,
    val name: String,
    val commune: CommuneDto
)
