package pl.stations

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.subjects.PublishSubject
import pl.domain.model.StationUiModel

class StationsAdapter : RecyclerView.Adapter<StationsViewHolder>() {

    private var stations = emptyList<StationUiModel>()
    var imageClickSubject: PublishSubject<Int> = PublishSubject.create()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationsViewHolder {
        return StationsViewHolder.from(parent)
    }

    override fun getItemCount() = stations.size

    override fun onBindViewHolder(holder: StationsViewHolder, position: Int) {
        val currentStation = stations[position]
        holder.bind(currentStation, imageClickSubject)
    }

    fun setData(newData: List<StationUiModel>) {
        val stationDIffUtil = StationDiffUtil(stations, newData)
        val diffUtilResult = DiffUtil.calculateDiff(stationDIffUtil)
        stations = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

    class StationDiffUtil<T>(
        private val oldList: List<T>,
        private val newList: List<T>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }
    }
}
