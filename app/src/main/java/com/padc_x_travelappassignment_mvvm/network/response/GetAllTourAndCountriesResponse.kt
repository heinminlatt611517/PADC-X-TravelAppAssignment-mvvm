package com.padc_x_travelappassignment_mvvm.network.response

import com.google.gson.annotations.SerializedName
import com.padc_x_travelappassignment_mvvm.data.vos.CountryVo

data class GetAllCountriesResponse(
    @SerializedName("code")
    val code : Int = 0,
    @SerializedName("message")
    val message : String = "",
    @SerializedName("data")
    val data : List<CountryVo>
)

data class GetAllToursResponse(
    @SerializedName("code")
    val code : Int = 0,
    @SerializedName("message")
    val message : String = "",
    @SerializedName("data")
    val data : List<CountryVo>
)