package com.dixitpatel.mycoffeevenue.mymodule.domain.model

import com.google.gson.annotations.SerializedName


data class CountriesModelItem(
    @SerializedName("capital")
    val capital: String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("latlng")
    private val latLng: List<Double>,
    @SerializedName("name")
    val name: String,
    @SerializedName("timezones")
    val timezones: List<String>
) {
    var selectedHomeLocation : String = "London"

    fun getLatitude(): Double {
        return latLng.getOrNull(0)?.toDouble() ?: 0.0
    }

    fun getLongitude(): Double {
        return latLng.getOrNull(1)?.toDouble() ?: 0.0
    }

    fun isHomeLocation(): Boolean{
        return name == selectedHomeLocation
    }
}

