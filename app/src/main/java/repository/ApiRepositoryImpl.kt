package repository

import Data.CallBackResource
import Data.HandleApi
import Data.ZooAnimalResult
import Data.ZooResult
import android.app.Application
import coil.ImageLoader
import com.example.myapplicationtest.R
import service.ZooService
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val appContext: Application,
    private val zooService: ZooService,
    private val imageLoader: ImageLoader,
): ApiRepository, HandleApi {

    init {
        val appName = appContext.getString(R.string.app_name)
    }

    override suspend fun networkCall(): CallBackResource<ZooResult> {
        return apiHandle { zooService.getZooLibrary("resourceAquire") }
    }

    override suspend fun getZooData(): CallBackResource<ZooAnimalResult> {
        return apiHandle { zooService.getZooAnimalData("resourceAquire")}
    }

    override fun getImageLoader(): ImageLoader {
        return imageLoader
    }
}
