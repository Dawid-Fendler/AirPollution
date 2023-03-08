package pl.data.service

import io.reactivex.rxjava3.core.Single
import pl.data.model.StationDetailsDto
import pl.data.model.StationDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AirPollutionApi {

    @GET("station/findAll")
    fun getStations(): Single<List<StationDto>>

    @GET("aqindex/getIndex/{stationId}")
    fun getAirPollutionDetails(
        @Path("stationId") id: Int,
    ): Single<StationDetailsDto>
}
