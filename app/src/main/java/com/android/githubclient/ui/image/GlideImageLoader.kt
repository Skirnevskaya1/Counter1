package com.android.githubclient.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.android.githubclient.domain.image.IImageLoader

class GlideImageLoader :
    IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .placeholder(com.android.githubclient.R.drawable.ic_badge_baseline)
            .into(container)
    }
}
