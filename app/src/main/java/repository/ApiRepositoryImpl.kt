package repository

import Data.ZooBody
import Data.ZooParams
import Data.ZooResult
import android.app.Application
import android.content.res.Resources
import android.util.Log
import com.example.myapplicationtest.R
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import remote.ZooApi
//import service.MyService
import service.ZooService
import timber.log.Timber
import javax.inject.Inject

class ApiRepositoryImpl(
//    private val api: ZooApi,
    private val appContext: Application,
    private val zooService: ZooService,
): ApiRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
    }

    override suspend fun networkCall(searchOption: String): ZooResult {
//        api.networkCall()
//        return emptyList<String>()
//        return MyService().repository.networkCall()
        return zooService.getZooLibrary(ZooParams(scope = searchOption)).apply {
            Log.d("34_789", "networkCall: $this")
        }
    }

    override suspend fun getZooData(): String {
        return zooService.getZooOpenData(ZooBody(ddd = ""))
    }
}

//@Module
//@InstallIn(ViewModelComponent::class)
//abstract class ZooRepositoryModule{
//
//    @Binds
//    abstract fun bindApiRepository(impl: ApiRepositoryImpl): ApiRepository
//}
