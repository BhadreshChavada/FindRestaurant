package com.findRestaurant.constant

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.findRestaurant.R
import java.io.File

class GlideLoader(internal  var context: Context) {
    private val simpleOptions = RequestOptions()
        .placeholder(R.drawable.ic_place_holder)
        .error(R.drawable.ic_place_holder)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

    fun loadImage(url: String, view: ImageView) {
        Glide.with(context)
            .load(url).apply(simpleOptions)
            .into(view)
    }




}