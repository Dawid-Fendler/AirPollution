package pl.stationdetails

import pl.domain.model.StationDetailsUiModel

sealed class StationDetailsViewState {
    data class StationDetailsLoaded(val data: StationDetailsUiModel) : StationDetailsViewState()
    object StationDetailsLoadFailure : StationDetailsViewState()
}
