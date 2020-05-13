package com.findRestaurant.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.findRestaurant.Utils.AppUtils
import com.findRestaurant.constant.AppConst
import com.findRestaurant.core.AppDatabase
import com.findRestaurant.core.BaseActivity
import com.findRestaurant.core.BaseViewModel
import com.findRestaurant.dao.RestaurantDao
import com.findRestaurant.dataService.ApiRequest
import com.findRestaurant.dataService.NetworkModule
import com.findRestaurant.model.Restaurant
import com.findRestaurant.model.RestaurantData
import com.litluxe.dataService.AppInteractor

class RestaurantListViewModel<T>(context: Activity, clazz: Class<T>) :
    BaseViewModel<T>(context, clazz) {

    val liveData: MutableLiveData<List<Restaurant>> = MutableLiveData()
    val emptyliveData: MutableLiveData<String> = MutableLiveData()
    private var GET_RESTAURANTS = 1
    var pageIndex = 1
    var totalCount = 0
    var restaurantDao: RestaurantDao

    init {
        restaurantDao = AppDatabase.getAppDatabase(activity!!)?.restaurantDao()!!
        getRestaurant(pageIndex)
    }


    fun getRestaurant(pageIndex: Int) {

        if (!AppUtils.hasInternet(activity)) {
            totalCount = restaurantDao.getCount()


            if(totalCount == 0){
                emptyliveData.value = ""
            }else{
                liveData.value = restaurantDao.loadRestaurantByPage(AppConst.OFFSET_LIMIT, pageIndex)
            }
        } else {

            AppInteractor.instance?.callGetServiceWithoutParameterApi(
                ApiRequest.GetRestaurants(pageIndex),
                NetworkModule.primaryService,
                this,
                GET_RESTAURANTS
            )

        }
    }

    override fun onResponse(response: RestaurantData, responseCode: Int) {
        onFinish()
        if (responseCode == GET_RESTAURANTS) {
            totalCount = response.totalEntries!!

            restaurantDao.insertAll(response.restaurants)

            if(totalCount == 0){
                emptyliveData.value = ""
            }else{
                liveData.value = response.restaurants
            }
        }

    }


}
