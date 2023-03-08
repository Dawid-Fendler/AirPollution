package pl.stations

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.architecture.BaseViewModel
import pl.architecture.SingleLiveEvent
import pl.domain.mapper.toUiModel
import pl.domain.usecase.GetStationsUseCase
import javax.inject.Inject

@HiltViewModel
class StationsViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase
) : BaseViewModel() {

    var stationsViewState: MutableLiveData<StationsViewState> =
        MutableLiveData(StationsViewState.Loading)
        private set

    var progressLoadingEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
        private set

    fun start() {
        getStations()
    }

    private fun getStations() {
        compositeDisposable.add(
            getStationsUseCase
                .execute(Unit)
                .doOnSubscribe { progressLoadingEvent.postValue(true) }
                .doOnTerminate { progressLoadingEvent.postValue(false) }
                .subscribe { result ->
                    when (result) {
                        is GetStationsUseCase.Result.Success -> stationsViewState.postValue(
                            StationsViewState.StationsLoaded(result.data.map { it.toUiModel() })
                        )
                        is GetStationsUseCase.Result.EmptyList -> stationsViewState.postValue(
                            StationsViewState.StationsEmpty
                        )
                        is GetStationsUseCase.Result.Failure -> stationsViewState.postValue(
                            StationsViewState.StationsLoadFailure
                        )
                    }
                }
        )
    }
}
