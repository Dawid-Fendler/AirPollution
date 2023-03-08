package pl.stationdetails

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.architecture.BaseViewModel
import pl.architecture.SingleLiveEvent
import pl.domain.mapper.toUiModel
import pl.domain.usecase.GetAirPollutionDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class StationDetailsViewModel @Inject constructor(
    private val getAirPollutionDetailsUseCase: GetAirPollutionDetailsUseCase
) : BaseViewModel() {

    var stationDetailsViewState: MutableLiveData<StationDetailsViewState> = MutableLiveData()
        private set

    var progressLoadingEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
        private set

    fun onStart(id: Int) {
        getAirPollutionDetails(id)
    }

    private fun getAirPollutionDetails(id: Int) {
        compositeDisposable.add(
            getAirPollutionDetailsUseCase
                .execute(id)
                .doOnSubscribe { progressLoadingEvent.postValue(true) }
                .doOnTerminate { progressLoadingEvent.postValue(false) }
                .subscribe { result ->
                    when (result) {
                        is GetAirPollutionDetailsUseCase.Result.Success ->
                            stationDetailsViewState.postValue(
                                StationDetailsViewState.StationDetailsLoaded(
                                    result.data.toUiModel()
                                )
                            )
                        is GetAirPollutionDetailsUseCase.Result.Failure ->
                            stationDetailsViewState.postValue(StationDetailsViewState.StationDetailsLoadFailure)
                    }
                }
        )
    }
}
