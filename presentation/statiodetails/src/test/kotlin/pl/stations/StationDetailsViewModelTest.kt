package pl.stations

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.domain.mapper.toUiModel
import pl.domain.usecase.GetAirPollutionDetailsUseCase
import pl.stationdetails.StationDetailsViewModel
import pl.stationdetails.StationDetailsViewState

class StationDetailsViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase: GetAirPollutionDetailsUseCase = mock()
    private lateinit var viewModelTest: StationDetailsViewModel

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        viewModelTest = StationDetailsViewModel(useCase)
    }

    @Test
    fun `show error snackbar, when get air pollution details returns Failure`() {
        val stationDetailsLiveDataObserver = mock<Observer<StationDetailsViewState>>()
        viewModelTest.stationDetailsViewState.observeForever(stationDetailsLiveDataObserver)
        whenever(useCase.execute(1)).thenReturn(
            Single.just(GetAirPollutionDetailsUseCase.Result.Failure)
        )

        viewModelTest.onStart(1)

        verify(stationDetailsLiveDataObserver).onChanged(StationDetailsViewState.StationDetailsLoadFailure)
    }

    @Test
    fun `load station details and map to uiModel , when get air pollution details returns Success`() {
        val stationDetailsLiveDataObserver = mock<Observer<StationDetailsViewState>>()
        viewModelTest.stationDetailsViewState.observeForever(stationDetailsLiveDataObserver)
        whenever(useCase.execute(1)).thenReturn(
            Single.just(
                GetAirPollutionDetailsUseCase.Result.Success(
                    stationDetails
                )
            )
        )

        viewModelTest.onStart(1)

        verify(stationDetailsLiveDataObserver).onChanged(
            StationDetailsViewState.StationDetailsLoaded(
                stationDetails.toUiModel()
            )
        )
    }

    @Test
    fun `fire progressLoadingEvent when station details are loading`() {
        val progressLoadingEventLiveDataObserver = mock<Observer<Boolean>>()
        viewModelTest.progressLoadingEvent.observeForever(progressLoadingEventLiveDataObserver)
        whenever(useCase.execute(1)).thenReturn(
            Single.just(
                GetAirPollutionDetailsUseCase.Result.Success(
                    stationDetails
                )
            )
        )

        viewModelTest.onStart(1)

        verify(progressLoadingEventLiveDataObserver, times(2)).onChanged(any())
    }
}