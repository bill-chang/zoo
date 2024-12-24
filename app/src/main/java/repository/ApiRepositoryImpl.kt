package repository

import Data.ZooAnimalResult
import Data.ZooResult
import android.app.Application
import com.example.myapplicationtest.R
import service.ZooService
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val appContext: Application,
    private val zooService: ZooService,
): ApiRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
    }

    override suspend fun networkCall(): ZooResult {
        return zooService.getZooLibrary("resourceAquire")
    }

    override suspend fun getZooData(): ZooAnimalResult {
        return zooService.getZooAnimalData("resourceAquire")
    }
}
