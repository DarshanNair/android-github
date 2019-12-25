package com.darshan.github.core.imageloader

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImageLoader constructor(private val glideRequestManager: RequestManager) {

    fun draw(view: ImageView, url: String) {
        glideRequestManager.load(url)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

}