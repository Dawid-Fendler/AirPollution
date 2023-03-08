package pl.stationdetails

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.statiodetails.R
import com.example.statiodetails.databinding.FragmnetStationDetailsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.BaseFragment
import pl.domain.model.StationDetailsUiModel

@AndroidEntryPoint
class StationDetailsFragment :
    BaseFragment<FragmnetStationDetailsBinding>(FragmnetStationDetailsBinding::inflate) {

    private val viewModel: StationDetailsViewModel by viewModels()
    private val arguments: StationDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeProgressLoadingEvent()
        observeRecipeListViewState()
        viewModel.onStart(arguments.details.id)
    }

    private fun observeProgressLoadingEvent() {
        viewModel
            .progressLoadingEvent
            .observe(viewLifecycleOwner) { visibility ->
                binding.animationView.isVisible = visibility
                binding.scrollView.isVisible = !visibility
            }
    }

    private fun observeRecipeListViewState() {
        viewModel
            .stationDetailsViewState
            .observe(viewLifecycleOwner) { state ->
                when (state) {
                    is StationDetailsViewState.StationDetailsLoaded ->
                        initView(state.data)
                    is StationDetailsViewState.StationDetailsLoadFailure ->
                        Snackbar.make(
                            binding.root,
                            R.string.station_details_data_loading_error,
                            Snackbar.LENGTH_LONG
                        ).show()
                }
            }
    }

    private fun initView(data: StationDetailsUiModel) {
        val stationUiModel = arguments.details
        initCityName(stationUiModel.cityName)
        initCityFullInformation(
            stationUiModel.communeName,
            stationUiModel.districtName,
            stationUiModel.provinceName
        )
        initAddress(stationUiModel.address)
        initAirPollutionInformation(data)
    }


    private fun initCityName(name: String) {
        binding.cityNameText.text = name
    }

    private fun initCityFullInformation(
        communeName: String,
        districtName: String,
        provinceName: String
    ) {
        binding.cityFullInformationText.text = requireContext().resources.getString(
            R.string.city_full_information,
            communeName,
            districtName,
            provinceName
        )
    }

    private fun initAddress(address: String) {
        binding.addressText.text = address
    }

    private fun initAirPollutionInformation(data: StationDetailsUiModel) {
        binding.airPollutionDescription.text = data.airPollutionDescription
    }
}
