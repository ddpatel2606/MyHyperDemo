package com.dixitpatel.mycoffeevenue.mymodule.data.repositoryIml


import android.content.Context
import com.dixitpatel.mycoffeevenue.mymodule.data.datasource.getCountries
import com.dixitpatel.mycoffeevenue.mymodule.data.datasource.getDistanceBetweenTwoLocation
import com.dixitpatel.mycoffeevenue.mymodule.domain.model.CountriesModelItem
import com.dixitpatel.mycoffeevenue.mymodule.domain.repository.MyLocationRepository
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class MyLocationRepositoryImpl @Inject constructor(@ApplicationContext val context: Context): MyLocationRepository {

    override suspend fun getCountriesList(): List<CountriesModelItem> {
        return getCountries(context)
    }

    override suspend fun calculateDistanceFromHomeLocation(starLocation: LatLng, endLocation: LatLng): Float {
        return getDistanceBetweenTwoLocation(starLocation, endLocation)
    }

    override suspend fun getShortestLocationPath(): Float {
        // Remaining
        return 0F
    }


}