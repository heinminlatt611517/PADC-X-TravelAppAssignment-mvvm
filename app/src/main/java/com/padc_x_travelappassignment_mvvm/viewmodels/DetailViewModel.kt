package com.padc_x_travelappassignment_mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.padc_x_travelappassignment_mvvm.data.model.TourModel
import com.padc_x_travelappassignment_mvvm.data.model.TourModelImpl
import com.padc_x_travelappassignment_mvvm.data.vos.CountryVo

class DetailViewModel : ViewModel() {
    private val mTourModel : TourModel = TourModelImpl

   fun getTourDetailLiveData(name : String) : LiveData<CountryVo>{
       return mTourModel.getToursByName(name)
   }
}