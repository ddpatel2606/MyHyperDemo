package com.dixitpatel.mycoffeevenue.di

import com.dixitpatel.mycoffeevenue.mymodule.data.repositoryIml.RandomTextRepositoryImpl
import com.dixitpatel.mycoffeevenue.mymodule.domain.repository.RandomTextRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 *  RepositoryModule
 *  Di will bind the RandomTextRepositoryImpl to RandomTextRepository
 *
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRandomTextRepository(repository: RandomTextRepositoryImpl): RandomTextRepository

}