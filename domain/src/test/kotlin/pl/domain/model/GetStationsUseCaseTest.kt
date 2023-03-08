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
import pl.domain.repository.AirPollutionRepository
import pl.domain.usecase.GetStationsUseCase

class GetStationsUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: AirPollutionRepository = mock()
    private val useCase = GetStationsUseCase(repository)

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `return failure when get air pollution returns failure`() {
        val throwable = Throwable()
        whenever(repository.getAirPollution()).thenReturn(Single.error(throwable))

        useCase
            .execute(Unit)
            .test()
            .assertValue(GetStationsUseCase.Result.Failure)
    }

    @Test
    fun `return empty list when get air pollution returns empty list`() {
        whenever(repository.getAirPollution()).thenReturn(Single.just(listOf()))

        useCase
            .execute(Unit)
            .test()
            .assertValue(GetStationsUseCase.Result.EmptyList)
    }

    @Test
    fun `return stations when get air pollution returns recipes`() {
        whenever(repository.getAirPollution()).thenReturn(Single.just(listOf(station)))

        useCase
            .execute(Unit)
            .test()
            .assertValue(GetStationsUseCase.Result.Success(listOf(station)))
    }
}