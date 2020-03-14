package com.padc_x_travelappassignment_mvvm.root

import android.app.Application
import android.content.Context
import com.padc_x_travelappassignment_mvvm.data.model.TourModelImpl

class TravelApp : Application() {
    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
        TourModelImpl.initDatabase(applicationContext)
    }
}