package com.example.studdy.di

import com.example.ExactAlarms
import com.example.studdy.ExactAlarmsImpl
import org.koin.dsl.module

val presentationModule = module {
    single<ExactAlarms> {
        ExactAlarmsImpl(get())
    }
}