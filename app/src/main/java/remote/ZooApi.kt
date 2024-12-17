package remote

import dagger.hilt.InstallIn
import retrofit2.http.GET

interface ZooApi {
    @GET("test")
    suspend fun networkCall()
}