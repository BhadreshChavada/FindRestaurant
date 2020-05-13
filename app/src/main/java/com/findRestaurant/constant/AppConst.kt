package com.findRestaurant.constant

import com.findRestaurant.BuildConfig

object AppConst {
    fun BASE_URL(): String {
        if (BuildConfig.FLAVOR.equals("dev")) {
//            return "https://dev.octosglobal.info/hopp/"
            return "http://opentable.herokuapp.com/api/"
        } else if (BuildConfig.FLAVOR.equals("qa")) {
            return "http://opentable.herokuapp.com/api/"
        } else if (BuildConfig.FLAVOR.equals("prod")) {
            return "http://opentable.herokuapp.com/api/"
        } else if (BuildConfig.FLAVOR.equals("stg")) {
            return "http://opentable.herokuapp.com/api/"
        } else {
            return "http://opentable.herokuapp.com/api/"
        }

    }


    val OFFSET_LIMIT = 25
    var CONNECTION_TIMEOUT = 15000

    val BUNDLE = "DATABUNDLE"
    val RESERVATIONURL = "RESERVATIONURL"
}