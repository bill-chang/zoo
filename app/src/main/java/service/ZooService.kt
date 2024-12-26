package service

import Data.ZooAnimalResult
import Data.ZooResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ZooService{

    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?")
    suspend fun getZooLibrary(@Query("scope") scope: String): Response<ZooResult>

    @GET("api/v1/dataset/a3e2b221-75e0-45c1-8f97-75acbd43d613?")
    suspend fun getZooAnimalData(@Query("scope") scope: String): Response<ZooAnimalResult>
}