package com.padc_x_travelappassignment_mvvm.data.model

import androidx.lifecycle.LiveData
import com.padc_x_travelappassignment_mvvm.data.vos.CountryVo
import com.padc_x_travelappassignment_mvvm.data.vos.ToursAndCountriesVO
import io.reactivex.Observable

interface TourModel {
  fun getAllTour() : Observable<ToursAndCountriesVO>

    fun getToursByName(name : String) : LiveData<CountryVo>
}