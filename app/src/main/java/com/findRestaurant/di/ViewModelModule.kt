package com.findRestaurant.di



import com.findRestaurant.Utils.AppUtils.Companion.activity
import com.findRestaurant.model.RestaurantData
import com.findRestaurant.viewmodel.*
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val viewModelModule = module(override = true) {
    viewModel { RestaurantListViewModel(activity,  RestaurantData::class.java) }
    viewModel { RestaurantMapViewModel(activity,  RestaurantData::class.java) }



}
