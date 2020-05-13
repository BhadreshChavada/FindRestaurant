package com.findRestaurant.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.findRestaurant.R
import com.findRestaurant.Utils.AppUtils
import com.findRestaurant.activities.ReserveTableActivity
import com.findRestaurant.adapter.RestaurantAdapter
import com.findRestaurant.base.BaseBindingAdapter
import com.findRestaurant.constant.AppConst
import com.findRestaurant.core.AppDatabase
import com.findRestaurant.core.BindingFragment
import com.findRestaurant.databinding.FragmentListRestaurantBinding
import com.findRestaurant.model.Restaurant
import com.findRestaurant.model.RestaurantData
import com.findRestaurant.utils.EndlessRecyclerViewScrollListener
import com.findRestaurant.viewmodel.RestaurantListViewModel

class RestaurantListFragment : BindingFragment<FragmentListRestaurantBinding>(),
    BaseBindingAdapter.ItemClickListener<Restaurant> {


    override fun getLayoutResId() = R.layout.fragment_list_restaurant
    var viewModel: RestaurantListViewModel<RestaurantData>? = null
    lateinit var adapter: RestaurantAdapter

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListRestaurantBinding.inflate(inflater, container, false)

        AppUtils.setActivtiy(activity!!)
        initViewModel()
        return binding.root
    }

    fun initViewModel() {

        viewModel = RestaurantListViewModel(activity!!, RestaurantData::class.java)

        initRecycleView()
        viewModel?.liveData?.observe(this, Observer { t ->
            if (viewModel!!.pageIndex == 1) {
                adapter.clear()
            }
            adapter.addItems(t)
            binding.rvRestaurant.visibility = VISIBLE
            binding.txtNoData.visibility = GONE
        })
        viewModel?.emptyliveData?.observe(this, Observer { t ->
            binding.rvRestaurant.visibility = GONE
            binding.txtNoData.visibility = VISIBLE
        })

    }

    fun initRecycleView() {
        adapter = RestaurantAdapter(activity!!)
        adapter.setItemClickListener(this)
        binding.rvRestaurant!!.adapter = adapter

        scrollListener = object :
            EndlessRecyclerViewScrollListener(binding.rvRestaurant.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if ((AppConst.OFFSET_LIMIT * viewModel?.pageIndex!!) < viewModel!!.totalCount) {

                    viewModel?.pageIndex = (viewModel!!.pageIndex) + 1
                    viewModel?.getRestaurant(viewModel?.pageIndex!!)
                }


            }
        }
        binding.rvRestaurant.addOnScrollListener(scrollListener)

    }

    override fun onClick(item: Restaurant, position: Int) {
        val bundle = Bundle()
        bundle.putSerializable(AppConst.RESERVATIONURL, item)
        lunchActivity(ReserveTableActivity::class.java, bundle)

    }

}