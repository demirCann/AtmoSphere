package com.demircandemir.atmosphere

import android.app.Application
import com.demircandemir.atmosphere.utils.PrepopulateDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    @Inject
    lateinit var prepopulateDatabase: PrepopulateDatabase

    override fun onCreate() {
        super.onCreate()

        applicationScope.launch {
            if (prepopulateDatabase.isDatabaseEmpty())
            prepopulateDatabase.prepopulateDatabase()
        }

    }


}