package com.padc_x_travelappassignment_mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padc_x_travelappassignment_mvvm.data.model.TourModel
import com.padc_x_travelappassignment_mvvm.data.model.TourModelImpl
import com.padc_x_travelappassignment_mvvm.data.vos.ToursAndCountriesVO
import com.padc_x_travelappassignment_mvvm.delegate.TourItemsDelegate
import io.reactivex.Observable

class HomeViewModel : ViewModel(), TourItemsDelegate{

    private val mTourModel : TourModel=TourModelImpl

    private val tourListObservdable : Observable<ToursAndCountriesVO> = mTourModel
        .getAllTour()

    private val navigateToDetailLiveData : MutableLiveData<String> = MutableLiveData()

    fun getAllTourData() : Observable<ToursAndCountriesVO>{
        return tourListObservdable
    }

    fun getNavigaetToDetailLiveData() : MutableLiveData<String>{
        return navigateToDetailLiveData
    }

    override fun onTapToursItem(name: String) {
      navigateToDetailLiveData.postValue(name)
    }






}