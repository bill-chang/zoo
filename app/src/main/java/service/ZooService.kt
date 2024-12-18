package service

import Data.ZooBody
import Data.ZooParams
import Data.ZooResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ZooService {

    @POST("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    suspend fun getZooLibrary(@Body params: ZooParams): ZooResult
//
    @GET("test")
//    suspend fun getZooOpenData(): String
    suspend fun getZooOpenData(@Body body: ZooBody): String
}