package repository

import Data.Posts
import Data.ZooAnimalResult
import Data.ZooResult

interface ApiRepository {

    suspend fun networkCall() : ZooResult

    suspend fun getZooData(): ZooAnimalResult
}