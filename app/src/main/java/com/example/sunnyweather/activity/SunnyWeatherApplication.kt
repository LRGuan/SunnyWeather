package com.example.sunnyweather.activity

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = "iclUJ4qv9BZUWsa8"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}