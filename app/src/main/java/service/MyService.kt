package service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import repository.ApiRepository
import javax.inject.Inject

@AndroidEntryPoint
class MyService @Inject constructor(): Service() {
    @Inject
    lateinit var repository: ApiRepository

    override fun onCreate() {
        super.onCreate()
//        repository.getZooData()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}