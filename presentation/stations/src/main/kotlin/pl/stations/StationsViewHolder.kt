package pl.stations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.subjects.PublishSubject
import pl.domain.model.StationUiModel
import pl.presentation.stations.databinding.RowStationBinding

class StationsViewHolder(
    private val binding: RowStationBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(result: StationUiModel, imageClickSubject: PublishSubject<Int>) {
        binding.stationName.text = result.stationName
        initImageOnClick(result, imageClickSubject)
    }

    private fun initImageOnClick(
        result: StationUiModel,
        imageClickSubject: PublishSubject<Int>
    ) {
        binding.root.setOnClickListener {
            imageClickSubject.onNext(result.id)
        }
    }

    companion object {
        fun from(parent: ViewGroup): StationsViewHolder = StationsViewHolder(
            RowStationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
