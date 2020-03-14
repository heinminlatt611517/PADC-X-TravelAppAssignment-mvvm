package com.padc_x_travelappassignment_mvvm.views.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc_x_travelappassignment_mvvm.data.vos.CountryVo
import com.padc_x_travelappassignment_mvvm.delegate.TourItemsDelegate
import kotlinx.android.synthetic.main.tour_list_items.view.*

class TourViewHolder(delegate: TourItemsDelegate,itemView: View) : BaseViewHolder<CountryVo>(itemView) {

    init {
        itemView.setOnClickListener {
            mData?.name?.let { delegate.onTapToursItem(it) }
        }

    }
    override fun bindData(data: CountryVo) {
      mData=data
        Glide.with(itemView.context)
            .load(data.photos?.get(0))
            .into(itemView.iv_popular_tour)

        itemView.txt_country_title.text=data.name
        itemView.rating.text=data.averagerating.toString()
    }
}