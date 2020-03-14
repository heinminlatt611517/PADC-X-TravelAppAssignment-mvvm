package com.padc_x_travelappassignment_mvvm.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.padc_x_travelappassignment_mvvm.data.vos.CountryVo
import com.padc_x_travelappassignment_mvvm.data.vos.ToursAndCountriesVO
import com.padc_x_travelappassignment_mvvm.presistence.db.AppDatabase
import com.padc_x_travelappassignment_mvvm.root.TravelApp.Companion.context
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

object  TourModelImpl : BaseModel(),TourModel{

    private val mDataBase: AppDatabase = AppDatabase.getNewDbInstance(context)
    private val countries = mTourApi.getAllCountries().map { it.data }
    private val tours = mTourApi.getAllTours().map { it.data }

    override fun getAllTour(): Observable<ToursAndCountriesVO> {
        return Observable.zip<List<CountryVo>,List<CountryVo>,ToursAndCountriesVO>(
            countries, tours, BiFunction { t1, t2 ->
                t1.forEach {
                    Log.d("Tours", it.toString())
                }
                return@BiFunction ToursAndCountriesVO(t1,t2)
            }
        ).doOnNext {
            val countriesAndToursList = arrayListOf(it.country,it.tours)
                .flatten()
            mDataBase.dao().saveAllTourData(countriesAndToursList.toMutableList())
        }
            .subscribeOn(Schedulers.io())
    }

    override fun getToursByName(name: String): LiveData<CountryVo> {
        return mDataBase.dao().getToursDatabyName(name)
    }
}