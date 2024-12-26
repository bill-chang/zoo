package repository

import Data.CallBackResource
import Data.ZooAnimalResult
import Data.ZooResult
import coil.ImageLoader

interface ApiRepository {

    suspend fun networkCall() : CallBackResource<ZooResult>

    suspend fun getZooData(): CallBackResource<ZooAnimalResult>

    fun getImageLoader(): ImageLoader
}