package com.findRestaurant.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RestaurantData {
    @SerializedName("total_entries")
    @Expose
    var totalEntries: Int? = null

    @SerializedName("per_page")
    @Expose
    var perPage: Int? = null

    @SerializedName("current_page")
    @Expose
    var currentPage: Int? = null

    @SerializedName("restaurants")
    @Expose
    var restaurants: List<Restaurant>? = null

}