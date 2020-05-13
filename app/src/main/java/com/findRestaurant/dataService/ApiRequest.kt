package com.findRestaurant.dataService

import com.findRestaurant.constant.AppConst


object ApiRequest {



    fun GetRestaurants(pageIndex: Int): String {
        return "restaurants?city=chicago&per_page=" + AppConst.OFFSET_LIMIT + "&page=" + pageIndex
    }
}

