package pl.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StationUiModel(
    val id: Int,
    val stationName: String,
    val cityName: String,
    val address: String,
    val communeName: String,
    val districtName: String,
    val provinceName: String
) : Parcelable
