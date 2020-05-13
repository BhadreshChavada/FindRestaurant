package com.findRestaurant.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.findRestaurant.R


class ImageBindingAdapter {
    companion object {

        private val simpleOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, url: String?) {
            try {
                if (url != null && url != "") {
                    Glide.with(imageView.context)
                            .load(url)
                            .apply(simpleOptions)
                            .into(imageView)
                }
            } catch (e: Exception) {
            }

        }



    }
}
