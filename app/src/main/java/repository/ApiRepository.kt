package repository

import android.content.res.Resources

interface ApiRepository {
    suspend fun networkCall() : String {
        return ""
    }

    suspend fun getZooData():String{
        return ""
    }
}