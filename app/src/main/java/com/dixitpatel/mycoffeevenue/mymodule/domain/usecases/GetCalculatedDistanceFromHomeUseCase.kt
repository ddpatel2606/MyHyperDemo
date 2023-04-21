package com.dixitpatel.mycoffeevenue.mymodule.domain.usecases

import com.dixitpatel.mycoffeevenue.mymodule.domain.repository.MyLocationRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetCalculatedDistanceFromHomeUseCase @Inject constructor(
    private val myLocationRepository: MyLocationRepository
) {
    operator fun invoke(starLocation:LatLng, endLocation:LatLng): Flow<Float> = flow {
        emit(
            myLocationRepository.calculateDistanceFromHomeLocation(starLocation,endLocation)
        )
    }
}