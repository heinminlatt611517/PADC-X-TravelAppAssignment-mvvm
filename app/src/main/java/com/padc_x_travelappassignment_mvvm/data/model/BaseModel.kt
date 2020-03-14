package com.padc_x_travelappassignment_mvvm.data.model

import android.content.Context
import com.padc_x_travelappassignment_mvvm.network.TourApi
import com.padc_x_travelappassignment_mvvm.presistence.db.AppDatabase
import com.padc_x_travelappassignment_mvvm.root.TravelApp
import com.padc_x_travelappassignment_mvvm.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {
    protected var mTourApi : TourApi
    protected lateinit var mDataBase: AppDatabase

    init {
        val okHttpClient=OkHttpClient.Builder()
            .connectTimeout(15,TimeUnit.SECONDS)
            .writeTimeout(15,TimeUnit.SECONDS)
            .readTimeout(15,TimeUnit.SECONDS)
            .build()

        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        mTourApi=retrofit.create(TourApi::class.java)
    }

    fun initDatabase(context : Context){
        mDataBase= AppDatabase.getNewDbInstance(context)
    }
}