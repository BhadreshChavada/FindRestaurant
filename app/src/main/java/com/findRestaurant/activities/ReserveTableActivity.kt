package com.findRestaurant.activities

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View.VISIBLE
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import com.findRestaurant.R
import com.findRestaurant.constant.AppConst
import com.findRestaurant.core.BindingActivity
import com.findRestaurant.databinding.ActivityReserveTableBinding
import com.findRestaurant.model.Restaurant
import kotlinx.android.synthetic.main.activity_reserve_table.*


class ReserveTableActivity : BindingActivity<ActivityReserveTableBinding>() {
    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_reserve_table
    lateinit var restaurant: Restaurant

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mBinding.toolbar?.ivBack?.visibility = VISIBLE
        initWebView()
        setWebClient()
        val bundle = getBundleFromIntent(intent)
        if (bundle != null) {
            restaurant = bundle.getSerializable(AppConst.RESERVATIONURL) as Restaurant
            mBinding.toolbar?.title?.text = restaurant.name

            if (restaurant.reserveUrl!!.startsWith("http:")) {

                loadUrl(restaurant.reserveUrl!!.replace("http:", "https:"))
            } else {
                loadUrl(restaurant.reserveUrl!!)
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.domStorageEnabled = false
        webView.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
                loadUrl(restaurant.reserveUrl!!)
            }
        }
    }

    private fun setWebClient() {
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                view: WebView,
                newProgress: Int
            ) {
                super.onProgressChanged(view, newProgress)

            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, exit the activity)
        return super.onKeyDown(keyCode, event)
    }

    private fun loadUrl(pageUrl: String) {
        webView.loadUrl(pageUrl)
    }


}