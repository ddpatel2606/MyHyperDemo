package com.dixitpatel.mycoffeevenue.mymodule.data.datasource

import android.content.Context
import android.location.Location
import com.dixitpatel.mycoffeevenue.mymodule.domain.model.CountriesModelItem
import com.dixitpatel.mycoffeevenue.mymodule.presentation.viewutils.readAssetsFile
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun getCountries(context: Context): List<CountriesModelItem> {
    val listType = object : TypeToken<List<CountriesModelItem?>>() {}.type
    return Gson().fromJson(context.assets.readAssetsFile("countries.json"), listType)
}

fun getDistanceBetweenTwoLocation(startLatLng: LatLng, endLatLng: LatLng): Float {
    val location1 = Location("")
    location1.latitude = startLatLng.latitude
    location1.longitude = startLatLng.longitude
    val location2 = Location("")
    location2.latitude = endLatLng.latitude
    location2.longitude = endLatLng.longitude
    return location1.distanceTo(location2)
}