package com.findRestaurant.constant

object AppConst {
    fun BASE_URL(): String {

        return "http://opentable.herokuapp.com/api/"


    }


    val OFFSET_LIMIT = 25
    var CONNECTION_TIMEOUT = 15000

    val BUNDLE = "DATABUNDLE"
    val RESERVATIONURL = "RESERVATIONURL"
}