package pl.domain.usecase

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.domain.model.Station
import pl.domain.repository.AirPollutionRepository
import pl.domain.util.UseCase
import javax.inject.Inject

class GetStationsUseCase @Inject constructor(
    private val repository: AirPollutionRepository
) : UseCase<Unit, Single<GetStationsUseCase.Result>> {

    override fun execute(input: Unit): Single<Result> {
        return repository
            .getAirPollution()
            .map {
                if (it.isEmpty()) {
                    Result.EmptyList
                } else {
                    Result.Success(it)
                }
            }
            .onErrorReturn {
                Log.d("Testowo", "execute:$it")
                Result.Failure
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    sealed class Result {
        data class Success(val data: List<Station>) : Result()
        object Failure : Result()
        object EmptyList : Result()
    }
}
