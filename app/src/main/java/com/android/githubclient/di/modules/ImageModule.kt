package com.android.githubclient.di.modules

import android.widget.ImageView
import com.android.githubclient.domain.image.IImageLoader
import com.android.githubclient.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}