package com.findRestaurant.dataService

import com.findRestaurant.model.RestaurantData
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.util.*


interface RestService {

    @GET
    fun apiGET(@Url urlt: String?): Observable<RestaurantData>?

}
