package service

import Data.Posts
import Data.ZooBody
import Data.ZooParams
import Data.ZooResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ZooService {

//    @POST("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
//    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    @GET("/posts")
//    suspend fun getZooLibrary(@Body params: ZooParams): ZooResult
//    suspend fun getZooLibrary(): ZooResult
    suspend fun getZooLibrary(): List<Posts>

//    @Query("scope")
//    suspend fun getZooLibrary(@Query("scope") scope: String): ZooResult

//
    @GET("test")
//    suspend fun getZooOpenData(): String
    suspend fun getZooOpenData(@Body body: ZooBody): String
}