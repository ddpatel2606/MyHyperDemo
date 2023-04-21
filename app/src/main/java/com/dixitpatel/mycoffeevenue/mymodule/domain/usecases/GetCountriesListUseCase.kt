package com.dixitpatel.mycoffeevenue.mymodule.domain.usecases

import com.dixitpatel.mycoffeevenue.mymodule.domain.model.CountriesModelItem
import com.dixitpatel.mycoffeevenue.mymodule.domain.repository.MyLocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetCountriesListUseCase @Inject constructor(
    private val myLocationRepository: MyLocationRepository
) {
    operator fun invoke(): Flow<List<CountriesModelItem>> = flow {
        emit(
            myLocationRepository.getCountriesList()
        )
    }
}