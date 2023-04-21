package com.dixitpatel.mycoffeevenue.mymodule.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dixitpatel.mycoffeevenue.mymodule.domain.usecases.GetCalculatedDistanceFromHomeUseCase
import com.dixitpatel.mycoffeevenue.mymodule.domain.usecases.GetCountriesListUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsActivityViewModel @Inject constructor(
    private val getCountriesListUseCase: GetCountriesListUseCase,
    private val getCalculatedDistanceFromHomeUseCase: GetCalculatedDistanceFromHomeUseCase
) : ViewModel() {

    private val _myLocationDataUiState = MutableStateFlow(MyLocationDataUiState())
    val myLocationDataUiState get() = _myLocationDataUiState.asStateFlow()

    private val _calculatedDistance = MutableStateFlow(MyLocationDistanceState())
    val calculatedDistance get() = _calculatedDistance.asStateFlow()

    fun setHomeLocation(locationName: String,locationLatLng: LatLng) {
        _myLocationDataUiState.update {
            it.copy(selectedHomeLocation = locationName, selectedHomeLatLng = locationLatLng)
        }
    }

    init {
        _myLocationDataUiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            getCountriesListUseCase().collect { data->
                _myLocationDataUiState.update {
                    it.copy(countriesList = data, isLoading = false)
                }
            }
        }
    }

    fun calculateDistanceFromHome(endLocation: LatLng) {
        _myLocationDataUiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            getCalculatedDistanceFromHomeUseCase(_myLocationDataUiState.value.selectedHomeLatLng,endLocation).collect { distance->
                _myLocationDataUiState.update {
                    it.copy(isLoading = false)
                }
                _calculatedDistance.update {
                   it.copy(calculatedDistance = distance)
                }
            }
        }
    }
}