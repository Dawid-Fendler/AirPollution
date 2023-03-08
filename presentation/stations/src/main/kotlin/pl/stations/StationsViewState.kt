package pl.stations

import pl.domain.model.StationUiModel

sealed class StationsViewState {
    data class StationsLoaded(val stations: List<StationUiModel>) : StationsViewState()
    object StationsLoadFailure : StationsViewState()
    object StationsEmpty : StationsViewState()
    object Loading : StationsViewState()
}
