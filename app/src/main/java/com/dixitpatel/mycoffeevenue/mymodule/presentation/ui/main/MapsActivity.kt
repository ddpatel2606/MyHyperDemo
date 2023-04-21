package com.dixitpatel.mycoffeevenue.mymodule.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.dixitpatel.mycoffeevenue.R
import com.dixitpatel.mycoffeevenue.databinding.ActivityMapsBinding
import com.dixitpatel.mycoffeevenue.mymodule.data.constant.DEFAULT_LONDON_LOCATION
import com.dixitpatel.mycoffeevenue.mymodule.domain.model.CountriesModelItem
import com.dixitpatel.mycoffeevenue.mymodule.presentation.ui.adapter.CountryListAdapter
import com.dixitpatel.mycoffeevenue.mymodule.presentation.ui.base.BaseActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MapsActivity : BaseActivity<ActivityMapsBinding>(R.layout.activity_maps), OnMapReadyCallback, CountryListAdapter.OnClickListener {

    private var mMap: GoogleMap? = null
    private val mapsActivityViewModel: MapsActivityViewModel by viewModels()
    private val mAdapter = CountryListAdapter(this@MapsActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MapsActivity
            toolbar.title = getString(R.string.app_name)
            adapter = mAdapter
            vm = mapsActivityViewModel
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap?.let { map ->
            map.isIndoorEnabled = false
            map.addMarker(MarkerOptions().position(DEFAULT_LONDON_LOCATION).title("London").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic__my_home)))
            val cameraPosition = CameraPosition.Builder().target(DEFAULT_LONDON_LOCATION).zoom(8f).build()
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    override fun onClickRecyclerItem(item: CountriesModelItem) {
        mMap?.let { map ->
            map.clear()
            val location = LatLng(item.getLatitude(), item.getLongitude())
            val marker = map.addMarker(
                MarkerOptions().position(location)
                    .icon(BitmapDescriptorFactory.fromResource( if(item.isHomeLocation()) R.drawable.ic__my_home else R.drawable.ic_pin))
                    .title(item.name).draggable(false)
                    .snippet("${item.capital}, ${item.countryCode}")
            )
            marker?.tag = item.name
            val cameraPosition = CameraPosition.Builder().target(location).zoom(8f).build()
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            marker?.showInfoWindow()
        }
    }

    override fun onClickSetHome(item: CountriesModelItem) {
        val locationDataUiState = LatLng(item.getLatitude(), item.getLongitude())
        item.selectedHomeLocation = item.name
        mapsActivityViewModel.setHomeLocation(item.name,locationDataUiState)
        onClickRecyclerItem(item)
        collectFlows(listOf(::collectAfterSetHome))
    }

    override fun onClickCalCalculateDistance(item: CountriesModelItem) {
        collectFlows(listOf(::collectCalculatedDistance))
        val endLocation = LatLng(item.getLatitude(), item.getLongitude())
        mapsActivityViewModel.calculateDistanceFromHome(endLocation)
    }

    private suspend fun collectAfterSetHome() {
        mapsActivityViewModel.myLocationDataUiState.collectLatest { uiStateData ->
            mAdapter.setCountriesList(uiStateData.countriesList)
        }
    }

    private suspend fun collectCalculatedDistance() {
        mapsActivityViewModel.calculatedDistance.collectLatest { uiStateData ->
            showSnackBar(getString(
                R.string.calculated_distance, uiStateData.calculatedDistance
            ), anchor = true)
        }
    }

}