package com.findRestaurant.core


import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.findRestaurant.R
import com.findRestaurant.Utils.AppUtils
import com.findRestaurant.constant.AppConst
import com.findRestaurant.constant.GlideLoader


open class BaseActivity : AppCompatActivity() {
    private var progressDialog: Dialog? = null


    var glideLoader: GlideLoader? = null


    val activity: Activity
        get() = this


//    public override fun onStart() {
//        super.onStart()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppUtils.setActivtiy(this)
        glideLoader = GlideLoader(activity)
    }

    fun getBundleFromIntent(intent: Intent?): Bundle? {
        var bundle: Bundle? = null
        if (intent != null && intent.getBundleExtra(AppConst.BUNDLE) != null) {
            bundle = intent.getBundleExtra(AppConst.BUNDLE)
        }
        return bundle
    }

    fun launchNewFirstActivity(activityTosrat: Class<*>) {
        val intent = Intent(applicationContext, activityTosrat)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        AppUtils.setActivtiy(this)
    }

    fun replaceFragmentInTab(fragment: Fragment, name: String, addtToBackStack: Boolean) {

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.container_home, fragment);
        if (addtToBackStack)
            ft.addToBackStack(name)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commit()
    }

    fun showProgressDialog(show: Boolean) {
        try {

            if (show) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun showProgressDialog() {
        try {
            if (progressDialog == null) {
                progressDialog = Dialog(this)
            } else {
                return
            }
            val view = LayoutInflater.from(this).inflate(R.layout.app_loading_dialog, null, false)

            progressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            progressDialog!!.setContentView(view)
            val window = progressDialog!!.window
            window?.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    android.R.color.transparent
                )
            )
            progressDialog!!.setCancelable(false)
            progressDialog!!.setCanceledOnTouchOutside(false)
            if (!progressDialog!!.isShowing && !activity.isFinishing)
                progressDialog!!.show()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    fun onBackPress(view: View) {
        onBackPressed()
    }
}