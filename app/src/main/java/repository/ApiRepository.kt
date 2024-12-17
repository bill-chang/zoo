package repository

import android.content.res.Resources

interface ApiRepository {
    suspend fun networkCall() : List<String> {
        return emptyList<String>()
    }

    suspend fun getZooData():String{
        return ""
    }
}