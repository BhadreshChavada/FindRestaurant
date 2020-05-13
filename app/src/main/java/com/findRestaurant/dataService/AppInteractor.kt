package com.litluxe.dataService

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.findRestaurant.FindRestaurant
import com.findRestaurant.Utils.AppUtils
import com.findRestaurant.dataService.InterActorCallback
import com.findRestaurant.dataService.RestService
import com.findRestaurant.model.RestaurantData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppInteractor {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("CheckResult")
    fun callGetServiceWithoutParameterApi(
        subURL: String,
        restService: RestService,
        callback: InterActorCallback,
        responseCode: Int
    ) {
        if (AppUtils.hasInternet(FindRestaurant.instance?.currentActivity!!)) {
            callback.onStart()
            restService.apiGET(subURL)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                    { o: RestaurantData? ->
                        callback.onResponse(
                            o!!,
                            responseCode
                        )
                    }
                ) { throwable: Throwable ->
                    callback.onError(
                        throwable.message!!,
                        responseCode
                    )
                }
        }
    }


    companion object {
        private const val TAG = "AppInteractor"
        var appInteractor: AppInteractor? = null
        val instance: AppInteractor?
            get() {
                if (appInteractor == null) {
                    appInteractor = AppInteractor()
                }
                return appInteractor
            }
    }


}