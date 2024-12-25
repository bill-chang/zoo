package module

import dagger.hilt.InstallIn

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
//import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import remote.ZooApi
import repository.ApiRepository
import repository.ApiRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.ZooService
import javax.inject.Singleton
//
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//
    @Provides
    @Singleton
    fun provideZooService(): ZooService{
     return Retrofit.Builder()
         .baseUrl("https://data.taipei")
         .addConverterFactory(GsonConverterFactory.create())
         .build()
         .create(ZooService::class.java)
    }


    @Provides
    @Singleton
    fun provideRepository(
        appContext: Application,
        zooService: ZooService
    ): ApiRepository {
        return ApiRepositoryImpl(appContext, zooService)
    }

}