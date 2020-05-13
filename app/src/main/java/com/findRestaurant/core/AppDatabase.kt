package com.findRestaurant.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.findRestaurant.dao.RestaurantDao
import com.findRestaurant.model.Restaurant


@Database(
    entities = [Restaurant::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao?

    companion object {
        private var instance: AppDatabase? = null


        fun getAppDatabase(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase::class.java,
                    "Restaurant-db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }

}