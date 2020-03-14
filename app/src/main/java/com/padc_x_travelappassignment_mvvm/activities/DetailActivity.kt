package com.padc_x_travelappassignment_mvvm.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.padc_x_travelappassignment_mvvm.R
import com.padc_x_travelappassignment_mvvm.data.vos.CountryVo
import com.padc_x_travelappassignment_mvvm.root.TravelApp.Companion.context
import com.padc_x_travelappassignment_mvvm.viewmodels.DetailViewModel
import com.padc_x_travelappassignment_mvvm.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_middle_layout.*

class DetailActivity : BaseActivity() {


    private lateinit var mViewModel : DetailViewModel

    companion object{
        const val TOURS_NAME_EXTRA="Tours Name Extra"

        fun newIntent(context: Context, tourName : String) : Intent {
            val intent= Intent(context,DetailActivity::class.java)
            intent.putExtra(TOURS_NAME_EXTRA,tourName)
            return intent
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tourName=intent.getStringExtra(TOURS_NAME_EXTRA)

        setUpViewModel()

        setUpDataByNameObservation(tourName)
    }

    private fun setUpDataByNameObservation(name : String){
        mViewModel.getTourDetailLiveData(name)
            .observe(this, Observer {
                bindData(it)
            })
    }

    private fun bindData(it  : CountryVo) {
        Glide.with(context!!)
            .load(it.photos[0])
            .into(iv_detail)
        tv_detail_dec.text=it.description
        tv_country_title.text=it.name
        tv_country_location.text=it.location
        tv_booking.text= it.scoresandreviews?.get(0)?.name
        tv_hotel.text= it.scoresandreviews?.get(1)?.name

        tv_booking_dec.text="Base on "+it.scoresandreviews?.get(0)?.total_reviews+" reviews"
        tv_booking_date.text= it.scoresandreviews?.get(0)?.score.toString()+"/"+it.scoresandreviews?.get(0)?.max_score.toString()

        tv_hotel_dec.text="Base on "+it.scoresandreviews?.get(1)?.total_reviews+" reviews"
        tv_hotel_date.text= it.scoresandreviews?.get(1)?.score.toString()+"/"+it.scoresandreviews?.get(1)?.max_score.toString()

        tv_ratingNo.text=it.averagerating.toString()
        rating.rating= it.averagerating.toFloat()
    }

    private fun setUpViewModel(){
        mViewModel=ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }
}
