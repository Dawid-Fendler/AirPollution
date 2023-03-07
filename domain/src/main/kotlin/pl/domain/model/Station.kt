package pl.domain.model

data class Station(
    val id: Int,
    val name: String,
    val latitude: String,
    val longitude: String,
    val city: City,
    val addressStreet: String?
)
