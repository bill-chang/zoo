package module

import android.app.Application
import coil.ImageLoader
import coil.imageLoader
import coil.memory.MemoryCache
import coil.util.DebugLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.ApiRepository
import repository.ApiRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.ZooService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    fun provideImageLoader(appContext: Application): ImageLoader {
        return appContext.imageLoader.newBuilder()
            .logger(DebugLogger())
            .memoryCache { MemoryCache.Builder(appContext).maxSizePercent(0.1).build() }
            .build()
    }


// imageLoader只是試做，在使用的頁面初始化就可以，這樣做有點殺雞焉用牛刀的感覺
// 而且不應該丟在apiRepository 只是因為不想再走一次做Repository跟impl的過程 所以這樣做
    @Provides
    @Singleton
    fun provideRepository(
        appContext: Application,
        zooService: ZooService,
        imageLoader: ImageLoader
    ): ApiRepository {
        return ApiRepositoryImpl(
            appContext,
            zooService,
            imageLoader
        )
    }
}