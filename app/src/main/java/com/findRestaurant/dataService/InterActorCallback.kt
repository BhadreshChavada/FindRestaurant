package com.findRestaurant.dataService

import com.findRestaurant.model.RestaurantData


interface InterActorCallback {

    fun onStart()

    fun onResponse(response: RestaurantData, responseCode: Int)

    fun onFinish()

    fun onError(message: String, responseCode: Int)


}
