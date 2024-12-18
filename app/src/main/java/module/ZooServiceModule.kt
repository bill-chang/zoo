package module
//
//import com.google.gson.Gson
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import service.ZooService
//import javax.inject.Singleton
//
//@InstallIn(SingletonComponent::class)
//@Module
//object ZooServiceModule {
//
//    @Provides
//    fun provideClient(): OkHttpClient {
//        return OkHttpClient().newBuilder().build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
//        return GsonConverterFactory.create(gson)
//    }
//
//    @Provides
//    @Singleton
//    fun provideMapApiService(
////        gsonConverterFactory: GsonConverterFactory,
//        client: OkHttpClient,
//    ):ZooService{
//        return Retrofit.Builder()
//            .client(client)
//            .baseUrl("https://data.taipei/")
////            .addConverterFactory(gsonConverterFactory)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ZooService::class.java)
//    }
//}