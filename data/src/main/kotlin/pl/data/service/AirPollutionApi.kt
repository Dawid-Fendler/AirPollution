package pl.data.service

import io.reactivex.rxjava3.core.Single
import pl.data.model.StationDto
import retrofit2.http.GET

interface AirPollutionApi {

    @GET("station/findAll")
    fun getStations(): Single<List<StationDto>>
}
