package pl.domain.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.domain.repository.AirPollutionDetailsRepository
import pl.domain.usecase.GetAirPollutionDetailsUseCase

class GetAirPollutionDetailsUseCaseTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: AirPollutionDetailsRepository = mock()
    private val useCase = GetAirPollutionDetailsUseCase(repository)

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `return failure when get air pollution details returns failure`() {
        val throwable = Throwable()
        whenever(repository.getAirPollutionDetails(1)).thenReturn(Single.error(throwable))

        useCase
            .execute(1)
            .test()
            .assertValue(GetAirPollutionDetailsUseCase.Result.Failure)
    }

    @Test
    fun `return stations when get air pollution details returns recipes`() {
        whenever(repository.getAirPollutionDetails(1)).thenReturn(Single.just(stationDetails))

        useCase
            .execute(1)
            .test()
            .assertValue(GetAirPollutionDetailsUseCase.Result.Success(stationDetails))
    }
}