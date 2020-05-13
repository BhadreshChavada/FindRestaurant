package com.findRestaurant.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.findRestaurant.Utils.AppUtils
import com.findRestaurant.base.BaseBindingAdapter
import com.findRestaurant.base.BaseBindingViewHolder
import com.findRestaurant.core.BaseActivity
import com.findRestaurant.databinding.AdapterRestaurantBinding
import com.findRestaurant.model.Restaurant


class RestaurantAdapter(internal var activity: Activity) :
    BaseBindingAdapter<Restaurant>() {

    var binding: AdapterRestaurantBinding? = null
    override fun bind(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewDataBinding {
        return AdapterRestaurantBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        val data = items!![position]
        val binding = holder.binding as AdapterRestaurantBinding
        binding.data = data
        (activity as BaseActivity).glideLoader?.loadImage(data.imageUrl!!, binding.ivRestaurant)
//
        binding.btnReserve.setOnClickListener(View.OnClickListener { onViewClick(position) })


        if (AppUtils.hasInternet(activity)) {
            binding.btnReserve.visibility = VISIBLE
        } else {
            binding.btnReserve.visibility = GONE
        }
    }


}