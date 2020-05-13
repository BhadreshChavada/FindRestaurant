package com.findRestaurant.activities

import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import com.findRestaurant.R
import com.findRestaurant.core.BaseActivity


class SplashActivity : BaseActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        Handler().postDelayed(
            Runnable { launchNewFirstActivity(HomeTabActivity::class.java) },
            3000
        )
    }


}