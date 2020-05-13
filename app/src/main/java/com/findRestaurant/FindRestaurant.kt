package com.findRestaurant

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import com.findRestaurant.Utils.AppUtils
import com.findRestaurant.di.apiModule
import com.findRestaurant.di.viewModelModule
import org.koin.android.ext.android.startKoin
import java.lang.ref.WeakReference
import javax.net.ssl.SSLContext

/**
 * @author Leopold
 */

class FindRestaurant : Application() {



    companion object {

        var instance: FindRestaurant? = null
    }

    private var mActivity: WeakReference<Activity>? = null

        val currentActivity: Activity
        get() = AppUtils.activity
    override fun onCreate() {
        super.onCreate()
        try {
            // Google Play will install latest OpenSSL
            ProviderInstaller.installIfNeeded(getApplicationContext());
            val sslContext: SSLContext
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            sslContext.createSSLEngine();
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace();
        }


        instance = this
        startKoin(this, listOf(
                apiModule,
                viewModelModule
        ))
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityResumed(activity: Activity?) {}

            override fun onActivityStarted(activity: Activity?) {}

            override fun onActivityDestroyed(activity: Activity?) {}

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

            override fun onActivityStopped(activity: Activity?) {}

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                mActivity = WeakReference(activity)

            }

            override fun onActivityPaused(activity: Activity?) {}
        })

    }

}