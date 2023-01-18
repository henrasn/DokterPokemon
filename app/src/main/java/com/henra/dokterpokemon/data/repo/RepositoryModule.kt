package com.henra.dokterpokemon.data.repo

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
//    @Binds
//    abstract fun bindPersonalRepo(personalRepository: PersonalRepositoryImpl): PersonalRepository
//
//    @Binds
//    abstract fun bindAddressRepo(addressRepositoryImpl: AddressRepositoryImpl): AddressRepository
}