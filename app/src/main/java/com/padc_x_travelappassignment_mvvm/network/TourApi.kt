package com.padc_x_travelappassignment_mvvm.network

import com.padc_x_travelappassignment_mvvm.network.response.GetAllCountriesResponse
import com.padc_x_travelappassignment_mvvm.network.response.GetAllToursResponse
import com.padc_x_travelappassignment_mvvm.utils.GET_ALL_COUNTRIES
import com.padc_x_travelappassignment_mvvm.utils.GET_ALL_TOURS
import io.reactivex.Observable
import retrofit2.http.GET

interface TourApi {
    @GET(GET_ALL_COUNTRIES)
    fun getAllCountries() : Observable<GetAllCountriesResponse>

    @GET(GET_ALL_TOURS)
    fun getAllTours() : Observable<GetAllToursResponse>
}