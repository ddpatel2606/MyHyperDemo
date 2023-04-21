package com.dixitpatel.mycoffeevenue.mymodule.presentation.ui.main

import com.dixitpatel.mycoffeevenue.mymodule.domain.model.CountriesModelItem
import com.google.android.gms.maps.model.LatLng

data class MyLocationDataUiState(
    val isLoading: Boolean = false,
    val countriesList: List<CountriesModelItem> = emptyList(),
    val selectedHomeLocation: String = "London",
    val selectedHomeLatLng: LatLng = LatLng(0.0,0.0)
)

data class MyLocationDistanceState(val calculatedDistance: Float = 0F)