package repository

import Data.ZooResult

interface ApiRepository {

    suspend fun networkCall(searchOption: String) : ZooResult

    suspend fun getZooData(): String
}