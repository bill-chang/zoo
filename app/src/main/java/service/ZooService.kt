package service

import Data.Posts
import Data.ZooAnimalResult
import Data.ZooParams
import Data.ZooResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ZooService {

//    @POST("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
//    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
//    @GET("/posts")
    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?")
//    suspend fun getZooLibrary(@Body params: ZooParams): ZooResult
    suspend fun getZooLibrary(@Query("scope") scope: String): ZooResult
//    suspend fun getZooLibrary(): List<Posts>

//    @Query("scope")
//    suspend fun getZooLibrary(@Query("scope") scope: String): ZooResult

//
    @GET("api/v1/dataset/a3e2b221-75e0-45c1-8f97-75acbd43d613?")
    suspend fun getZooAnimalData(@Query("scope") scope: String): ZooAnimalResult
}