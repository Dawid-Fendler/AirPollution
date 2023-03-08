package pl.stations

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.BaseFragment
import pl.domain.model.StationUiModel
import pl.presentation.stations.R
import pl.presentation.stations.databinding.FragmentStationsBinding
import pl.stations.adapter.StationsAdapter
import java.util.concurrent.TimeUnit

@SuppressWarnings("TooManyFunctions")
@AndroidEntryPoint
class StationsFragment : BaseFragment<FragmentStationsBinding>(FragmentStationsBinding::inflate) {

    private val viewModel: StationsViewModel by viewModels()

    private val adapter by lazy { StationsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setListeners()

        observeProgressLoadingEvent()
        observeStationsViewState()
        navigateToStationDetails()
        viewModel.start()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.start()
        }
    }

    private fun observeProgressLoadingEvent() {
        viewModel.progressLoadingEvent.observe(viewLifecycleOwner) { isRefreshing ->
            binding.swipeRefreshLayout.isRefreshing = isRefreshing
        }
    }

    private fun observeStationsViewState() {
        viewModel.stationsViewState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is StationsViewState.StationsLoaded -> showStationsLoadedState(result.stations)
                is StationsViewState.StationsLoadFailure -> showStationsLoadFailureState()
                is StationsViewState.StationsEmpty -> showStationsEmptyState()
                is StationsViewState.Loading -> showLoadingState()
            }
        }
    }

    private fun showStationsLoadedState(stations: List<StationUiModel>) {
        initScreenOptions(refreshLayoutIsVisible = true)
        adapter.setData(stations)
    }

    private fun showLoadingState() {
        initScreenOptions()
    }

    private fun showStationsEmptyState() {
        initScreenOptions(
            errorViewIsVisible = true,
            errorViewText = requireContext().resources.getString(R.string.stations_empty_text)
        )
    }

    private fun showStationsLoadFailureState() {
        initScreenOptions(rootLayoutIsVisible = false)
        Toast.makeText(
            requireContext(),
            requireContext().resources.getText(R.string.stations_refresh_screen_text),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun initScreenOptions(
        refreshLayoutIsVisible: Boolean = false,
        rootLayoutIsVisible: Boolean = true,
        errorViewIsVisible: Boolean = false,
        errorViewText: String = ""
    ) {
        with(binding) {
            swipeRefreshLayout.isVisible = refreshLayoutIsVisible
            rootLayout.isVisible = rootLayoutIsVisible
            errorView.isVisible = errorViewIsVisible
            errorView.errorText = errorViewText
        }
    }

    private fun navigateToStationDetails() {
        compositeDisposable.add(
            adapter.imageClickSubject
                .throttleFirst(500L, TimeUnit.MILLISECONDS)
                .subscribe {
                    findNavController().navigate(
                        StationsFragmentDirections.actionStationsFragmentToStationGraph(it)
                    )
                }
        )
    }
}
