package com.findRestaurant.core

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.findRestaurant.R
import com.findRestaurant.constant.AppConst


abstract class BaseFragment : Fragment() {
    var progress: ProgressBar? = null
    private var progressDialog: Dialog? = null
        private set

    fun showProgressDialog(show: Boolean) {
        //Show Progress bar here
        if (show) {
            showProgressBar()
        } else {
            hideProgressBar()
        }
    }

    fun lunchActivity(activityTostart: Class<*>, bundle: Bundle) {
        val intent = Intent(activity, activityTostart)
        intent.putExtra(AppConst.BUNDLE, bundle)
        activity!!.startActivity(intent)
    }

    protected fun showProgressBar() {
        if (progressDialog == null) {
            progressDialog = Dialog(activity!!)
        }
        val view = LayoutInflater.from(activity).inflate(R.layout.app_loading_dialog, null, false)



        progressDialog!!.setContentView(view)
        val window = progressDialog!!.window
        window?.setBackgroundDrawable(ContextCompat.getDrawable(activity!!, android.R.color.transparent))
        progressDialog!!.setCancelable(false)
        progressDialog!!.setCanceledOnTouchOutside(false)
        progressDialog!!.show()
    }

    protected fun hideProgressBar() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
