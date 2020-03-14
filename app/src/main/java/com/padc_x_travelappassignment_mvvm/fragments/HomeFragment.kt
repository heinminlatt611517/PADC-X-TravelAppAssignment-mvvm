package com.padc_x_travelappassignment_mvvm.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.padc_x_travelappassignment_mvvm.R
import com.padc_x_travelappassignment_mvvm.activities.DetailActivity
import com.padc_x_travelappassignment_mvvm.adapter.CountryListAdapter
import com.padc_x_travelappassignment_mvvm.adapter.TourListAdapter
import com.padc_x_travelappassignment_mvvm.viewmodels.HomeViewModel
import com.padc_x_travelappassignment_mvvm.views.viewpods.EmptyViewPod
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_home.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"




class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mViewModel : HomeViewModel
    private var compositeDisposable : CompositeDisposable = CompositeDisposable()
    lateinit var mTourListAdapter : TourListAdapter
    lateinit var mPopularListAdapter: CountryListAdapter

    private lateinit var viewPodEmpty: EmptyViewPod


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()

        setUpTourRecyclerView()
        setUpCountryRecyclerView()

        setUpSwipeRefershListener()
        setUpDataObservation()
        setUpViewPod()
    }

    private fun setUpCountryRecyclerView(){
        mPopularListAdapter = CountryListAdapter(mViewModel)
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recycler_country.layoutManager = layoutManager
        recycler_country.adapter = mPopularListAdapter
    }

    private fun setUpTourRecyclerView(){
        mTourListAdapter= TourListAdapter(mViewModel)
        val linearLayoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        recycler_popular.layoutManager=linearLayoutManager
        recycler_popular.adapter= mTourListAdapter
    }

    private fun setUpDataObservation(){
        enableSwipeRefresh()
        mViewModel.getAllTourData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                disableSwipeRefresh()
                mPopularListAdapter.setNewData(it.country.toMutableList())
                mTourListAdapter.setNewData(it.tours.toMutableList())
            }.addTo(compositeDisposable)

        mViewModel.getNavigaetToDetailLiveData().observe(
            this, Observer {
                startActivity(context?.let { it1 -> DetailActivity.newIntent(it1,it) })
            }
        )

    }

    private fun setUpViewPod() {
        viewPodEmpty = vpEmpty as EmptyViewPod
        viewPodEmpty.setEmptyData(
            "There are no news available",
            "https://point-broadband.com/wp-content/uploads/2017/06/No-data-caps-graphic-e1497904686711.png"
        )
    }

    private fun setUpSwipeRefershListener(){
        swipeRefreshLayout.setOnRefreshListener {
            enableSwipeRefresh()
            mViewModel.getAllTourData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    disableSwipeRefresh()
                    mPopularListAdapter.setNewData(it.country.toMutableList())
                    mTourListAdapter.setNewData(it.tours.toMutableList())
                }.addTo(compositeDisposable)
        }

    }

    private fun setUpViewModel(){
        mViewModel=ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

   private fun enableSwipeRefresh() {
        swipeRefreshLayout.isRefreshing=true
    }

   private fun disableSwipeRefresh() {
        swipeRefreshLayout.isRefreshing=false
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
