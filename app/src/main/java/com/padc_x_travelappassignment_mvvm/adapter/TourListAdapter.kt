package com.padc_x_travelappassignment_mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc_x_travelappassignment_mvvm.R
import com.padc_x_travelappassignment_mvvm.data.vos.CountryVo
import com.padc_x_travelappassignment_mvvm.delegate.TourItemsDelegate
import com.padc_x_travelappassignment_mvvm.views.viewholder.BaseViewHolder
import com.padc_x_travelappassignment_mvvm.views.viewholder.TourViewHolder

class TourListAdapter(delegate: TourItemsDelegate) : BaseRecyclerAdapter<BaseViewHolder<CountryVo>,CountryVo>() {
    val mDelegate : TourItemsDelegate=delegate
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CountryVo> {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.tour_list_items, parent, false)
        return TourViewHolder(mDelegate,view)
    }
}