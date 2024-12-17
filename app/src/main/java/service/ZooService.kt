package service

import retrofit2.http.GET

interface ZooService {
    @GET("https://data.taipei/#/dataset/detail?id=1ed45a8a-d26a-4a5f-b544-788a4071eea2")
    suspend fun getZooOpenData(): String
}