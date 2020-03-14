package com.padc_x_travelappassignment_mvvm.root

import android.app.Application
import android.content.Context

class TravelApp : Application() {
    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}