package com.findRestaurant.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class Restaurant : Serializable {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("area")
    @Expose
    var area: String? = null

    @SerializedName("postal_code")
    @Expose
    var postalCode: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("lat")
    @Expose
    var lat: Double? = null

    @SerializedName("lng")
    @Expose
    var lng: Double? = null

    @SerializedName("price")
    @Expose
    var price: Int? = null

    @SerializedName("reserve_url")
    @Expose
    var reserveUrl: String? = null

    @SerializedName("mobile_reserve_url")
    @Expose
    var mobileReserveUrl: String? = null

    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null

    fun fullAddress(): String {
        return address + ", " + city + ", " + state + ", " + country + ", " + postalCode
    }

}