package com.app.myapplication.util.extension

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.app.myapplication.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

@BindingAdapter(
    "glideUrl",
    "placeholder",
    requireAll = false
)
fun ImageView.loadProfile(
    data: Any?,
    @DrawableRes placeholder: Int = -1,
) {
    val request = Glide.with(this)
        .load(data)
        .placeholder(placeholder)
        .error(placeholder)
        .apply(RequestOptions.circleCropTransform())

    if (BuildConfig.DEBUG)
        request.addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
            ): Boolean {
                Log.v("Glide", "Image load failed, url = $data")
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                Log.v("Glide", "Image loaded, url = $data")
                return false
            }
        })
    request.into(this)
}