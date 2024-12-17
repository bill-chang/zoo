package repository

import android.app.Application
import android.content.res.Resources
import com.example.myapplicationtest.R
import remote.ZooApi
//import service.MyService
import service.ZooService
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
//    private val api: ZooApi,
    private val appContext: Application,
    private val zooService: ZooService,
): ApiRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
    }

    override suspend fun networkCall(): String {
//        api.networkCall()
//        return emptyList<String>()
//        return MyService().repository.networkCall()
        return zooService.getZooOpenData()
    }

    override suspend fun getZooData(): String {
        return zooService.getZooOpenData()
    }
}
