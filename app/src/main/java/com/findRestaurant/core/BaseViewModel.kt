package com.findRestaurant.core

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.findRestaurant.Utils.AppUtils
import com.findRestaurant.dataService.InterActorCallback
import com.findRestaurant.model.RestaurantData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BaseViewModel<T>(internal var activity: Activity, clazz: Class<T>?) : ViewModel(),

    InterActorCallback {
    val RESEND_EMAIL = 333
    var instance: T? = null


    private val disposables: CompositeDisposable = CompositeDisposable()
    internal fun createContents(clazz: Class<T>): T? {
        try {
            this.instance = clazz.newInstance()
            return this.instance
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            return null
        } catch (e2: InstantiationException) {
            e2.printStackTrace()
            return null
        }

    }

    init {

        if (clazz != null) {
            createContents(clazz)
        }
    }

    fun emptyViewVisibility(view: View, size: Int) {
        view.visibility = if (size == 0) View.VISIBLE else View.GONE
    }

    override fun onStart() {
        (this.activity as BaseActivity).showProgressDialog()
    }

    override fun onFinish() {
        (this.activity as BaseActivity).showProgressDialog(false)
    }

    override fun onResponse(response: RestaurantData, responseCode: Int) {
        onFinish()
    }

    override fun onError(message: String, responseCode: Int) {


        AppUtils.showErrorSnackBar(message)
        val stringBuilder = StringBuilder()
        stringBuilder.append("onError: ")
        stringBuilder.append(message)
        Log.i("TAG", stringBuilder.toString())
        onFinish()
    }


    fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    var view: View? = null
    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }


}

