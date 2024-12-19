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
//         .baseUrl("https://data.taipei/#/dataset/detail?id=1ed45a8a-d26a-4a5f-b544-788a4071eea2")
         .baseUrl("https://jsonplaceholder.typicode.com")
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