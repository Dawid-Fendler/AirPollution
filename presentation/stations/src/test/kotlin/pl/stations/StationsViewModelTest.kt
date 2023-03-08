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
import pl.domain.usecase.GetStationsUseCase

class StationsViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase: GetStationsUseCase = mock()
    private lateinit var viewModelTest: StationsViewModel

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        viewModelTest = StationsViewModel(useCase)
    }

    @Test
    fun `show empty state screen, when get stations returns EmptyList`() {
        val stationsLiveDataObserver = mock<Observer<StationsViewState>>()
        viewModelTest.stationsViewState.observeForever(stationsLiveDataObserver)
        whenever(useCase.execute(Unit)).thenReturn(Single.just(GetStationsUseCase.Result.EmptyList))

        viewModelTest.start()

        verify(stationsLiveDataObserver).onChanged(StationsViewState.StationsEmpty)
    }

    @Test
    fun `show error snackbar, when get stations returns Failure`() {
        val stationsLiveDataObserver = mock<Observer<StationsViewState>>()
        viewModelTest.stationsViewState.observeForever(stationsLiveDataObserver)
        whenever(useCase.execute(Unit)).thenReturn(
            Single.just(GetStationsUseCase.Result.Failure)
        )

        viewModelTest.start()

        verify(stationsLiveDataObserver).onChanged(StationsViewState.StationsLoadFailure)
    }

    @Test
    fun `load stations and map to uiModel , when get stations returns Success`() {
        val stationsDataObserver = mock<Observer<StationsViewState>>()
        viewModelTest.stationsViewState.observeForever(stationsDataObserver)
        whenever(useCase.execute(Unit)).thenReturn(
            Single.just(
                GetStationsUseCase.Result.Success(
                    listOf(station)
                )
            )
        )

        viewModelTest.start()

        verify(stationsDataObserver).onChanged(
            StationsViewState.StationsLoaded(
                listOf(station).map { it.toUiModel() }
            )
        )
    }

    @Test
    fun `fire progressLoadingEvent when stations are loading`() {
        val progressLoadingEventLiveDataObserver = mock<Observer<Boolean>>()
        viewModelTest.progressLoadingEvent.observeForever(progressLoadingEventLiveDataObserver)
        whenever(useCase.execute(Unit)).thenReturn(
            Single.just(
                GetStationsUseCase.Result.Success(
                    listOf(station)
                )
            )
        )

        viewModelTest.start()

        verify(progressLoadingEventLiveDataObserver, times(2)).onChanged(any())
    }
}