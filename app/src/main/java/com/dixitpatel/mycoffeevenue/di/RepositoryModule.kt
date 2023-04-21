package com.dixitpatel.mycoffeevenue.di

import com.dixitpatel.mycoffeevenue.mymodule.data.repositoryIml.MyLocationRepositoryImpl
import com.dixitpatel.mycoffeevenue.mymodule.domain.repository.MyLocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 *  RepositoryModule
 *  Di will bind the MyLocationRepositoryImpl to MyLocationRepository
 *
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRandomTextRepository(repository: MyLocationRepositoryImpl): MyLocationRepository

}