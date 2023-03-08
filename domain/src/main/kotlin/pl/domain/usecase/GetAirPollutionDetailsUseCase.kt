package pl.domain.usecase

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.domain.model.StationDetails
import pl.domain.repository.AirPollutionDetailsRepository
import pl.domain.util.UseCase
import javax.inject.Inject

class GetAirPollutionDetailsUseCase @Inject constructor(
    private val repository: AirPollutionDetailsRepository
) : UseCase<Int, Single<GetAirPollutionDetailsUseCase.Result>> {

    override fun execute(input: Int): Single<Result> {
        return repository
            .getAirPollutionDetails(input)
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure as Result }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    sealed class Result {
        data class Success(val data: StationDetails) : Result()
        object Failure : Result()
    }
}
