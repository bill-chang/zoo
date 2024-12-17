package com.example.myapplicationtest.ui.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import remote.ZooApi
import repository.ApiRepository
import repository.ApiRepositoryImpl
import retrofit2.Retrofit
import service.ZooService
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
//    @Singleton
    fun provideZooApi(): ZooApi{
     return Retrofit.Builder()
         .baseUrl("https://data.taipei/#/dataset/detail?id=1ed45a8a-d26a-4a5f-b544-788a4071eea2")
         .build()
         .create(ZooApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideRepository(api: ZooApi, appContext: Application): ApiRepository {
//        return ApiRepositoryImpl(api, appContext)
//    }

    @Provides
//    @Singleton
    fun provideRepository(api: ZooApi, appContext: Application, zooService: ZooService): ApiRepository {
        return ApiRepositoryImpl(appContext, zooService)
    }
}