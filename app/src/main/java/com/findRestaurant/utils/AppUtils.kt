package com.findRestaurant.Utils


import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.findRestaurant.R


class AppUtils {
    companion object {

        lateinit var activity: Activity

        fun setActivtiy(activtiy: Activity) {
            activity = activtiy
        }


        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        


        fun showErrorSnackBar(s: String) {
            try {
                val snack =
                    Snackbar.make(activity.window.decorView.rootView, s, Snackbar.LENGTH_LONG)
                val sbview = snack.view
                sbview.setBackgroundColor(
                    ContextCompat.getColor(
                        activity,
                        android.R.color.holo_red_dark
                    )
                )
                val textView = sbview.findViewById<View>(R.id.snackbar_text) as TextView
                textView.setTextColor(ContextCompat.getColor(activity, android.R.color.white))
                snack.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }




        fun showSnackBar(s: String) {
            val snack = Snackbar.make(activity.window.decorView.rootView, s, Snackbar.LENGTH_LONG)
            val sbview = snack.view
            sbview.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.black))
            val textView = sbview.findViewById<View>(R.id.snackbar_text) as TextView
            textView.setTextColor(ContextCompat.getColor(activity, android.R.color.white))

//            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(activity.window.decorView.rootView.getWindowToken(), 0)

            snack.show()


        }



        fun hasInternet(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return cm.activeNetworkInfo != null
        }


    }
}
