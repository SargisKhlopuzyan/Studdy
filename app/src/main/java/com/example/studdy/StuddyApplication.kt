package com.example.studdy

import android.app.Application
import com.example.ExactAlarms
import com.example.studdy.di.presentationModule
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StuddyApplication : Application() {

    val exactAlarms: ExactAlarms by inject()

    val alarmRingtoneState = MutableStateFlow("")

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StuddyApplication)
            modules(presentationModule)
        }
    }
}