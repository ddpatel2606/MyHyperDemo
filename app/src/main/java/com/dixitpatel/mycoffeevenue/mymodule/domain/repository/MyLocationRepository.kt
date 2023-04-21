package com.dixitpatel.mycoffeevenue.mymodule.domain.repository

import com.dixitpatel.mycoffeevenue.mymodule.domain.model.CountriesModelItem
import com.google.android.gms.maps.model.LatLng

interface MyLocationRepository {
    suspend fun getCountriesList(): List<CountriesModelItem>

    suspend fun calculateDistanceFromHomeLocation(starLocation: LatLng, endLocation: LatLng): Float

    suspend fun getShortestLocationPath(): Float

}