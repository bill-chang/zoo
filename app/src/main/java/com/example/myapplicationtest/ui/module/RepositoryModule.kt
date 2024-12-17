package com.example.myapplicationtest.ui.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import repository.ApiRepository
import repository.ApiRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        myRepositoryImpl: ApiRepositoryImpl
    ): ApiRepository
}