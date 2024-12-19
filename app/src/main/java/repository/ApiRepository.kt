package repository

import Data.Posts
import Data.ZooResult

interface ApiRepository {

//    suspend fun networkCall(searchOption: String) : ZooResult
//    suspend fun networkCall() : ZooResult
    suspend fun networkCall() : List<Posts>

    suspend fun getZooData(): String
}