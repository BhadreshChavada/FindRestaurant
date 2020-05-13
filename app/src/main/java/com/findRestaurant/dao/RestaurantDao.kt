package com.findRestaurant.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.findRestaurant.model.Restaurant


@Dao
interface RestaurantDao {

    @Query("SELECT * FROM Restaurant")
    fun getAllRestaurant(): List<Restaurant?>?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurant(restaurant: Restaurant?)


    @Query("DELETE FROM Restaurant")
    fun deleteRestaurant()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(restaurantLists: List<Restaurant?>?)


    @Query("SELECT * FROM Restaurant user LIMIT :limit OFFSET :offset")
    fun loadRestaurantByPage(limit: Int, offset: Int): List<Restaurant>?

    @Query("SELECT COUNT(id) FROM Restaurant")
    fun getCount(): Int
}